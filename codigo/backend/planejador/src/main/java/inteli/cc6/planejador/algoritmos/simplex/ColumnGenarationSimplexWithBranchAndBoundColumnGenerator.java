package inteli.cc6.planejador.algoritmos.simplex;

import inteli.cc6.planejador.algoritmos.newPatternGen.BranchAndBoundPatternGenerator;
import inteli.cc6.planejador.algoritmos.patterns.Pattern;
import inteli.cc6.planejador.algoritmos.reels.Reel;
import inteli.cc6.planejador.algoritmos.reels.ReelList;
import inteli.cc6.planejador.algoritmos.results.SolutionCombination;
import org.apache.commons.math3.linear.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ColumnGenarationSimplexWithBranchAndBoundColumnGenerator {

    public static SolutionCombination solve(ReelList reelList){
        ArrayList<Integer> widths = reelList.getWidthsList();
        ArrayList<Integer> demands = reelList.getDemandList();
        double[] objectiveFunction = new double[reelList.getReelList().size()];
        Arrays.fill(objectiveFunction, 1);
        double[][] currentMatrix = getHomogeneousMatrix(reelList.getReelListJumboSizeMax(), widths);

        while (true){
            double[] solution = getCurrentSolution(currentMatrix, demands);
            double[] relativeCosts = getRelativeCosts(currentMatrix, objectiveFunction);
            Pattern pattern = BranchAndBoundPatternGenerator.generateBranchAndBoundPattern(relativeCosts, reelList);
            if (solutionIsOptimal(relativeCosts, pattern.getPatternVectorDouble(reelList.getReelList().size()))){
                return new SolutionCombination(reelList, solution, currentMatrix);
            }
            int leavingColumn = getLeavingColumn(currentMatrix, solution, pattern.getPatternVectorDouble(reelList.getReelList().size()));
            currentMatrix = switchColumn(currentMatrix, pattern.getPatternVector(reelList.getReelList().size()), leavingColumn);
        }
    }

    private static double[][] getHomogeneousMatrix(int jumboSize, ArrayList<Integer> widths){
        int n = widths.size();
        double[][] homogeneousMatrix = new double[widths.size()][widths.size()];

        for (int i = 0, j = 0; i < n; i++, j++) {
            homogeneousMatrix[i][j] = (int) (jumboSize / widths.get(i).doubleValue());
        }

        return homogeneousMatrix;
    }

    private static double[] getCurrentSolution(double[][] currentMatrix, ArrayList<Integer> demands){

        double[] newDemands = new double[demands.size()];

        for (int i = 0; i < demands.size(); i++) {
            newDemands[i] = demands.get(i).doubleValue();
        }

        RealMatrix matrix = new Array2DRowRealMatrix(currentMatrix);
        RealVector vector = new ArrayRealVector(demands.stream().mapToDouble(Integer::doubleValue).toArray());

        DecompositionSolver solver;
        double[] solution;

        solver = new LUDecomposition(matrix).getSolver();
        RealVector solutionVector = solver.solve(vector);
        solution = solutionVector.toArray();

        return solution;
    }

    private static double[] getRelativeCosts(double[][] currentMatrix, double[] objectiveFunction){

        RealMatrix transposedMatrix = new Array2DRowRealMatrix(currentMatrix).transpose();
        RealVector relativeCostsVector = new ArrayRealVector(objectiveFunction);

        DecompositionSolver solver;
        double[] piVector;
        solver = new LUDecomposition(transposedMatrix).getSolver();
        RealVector piVectorVector = solver.solve(relativeCostsVector);
        piVector = piVectorVector.toArray();

        return piVector;
    }

    private static int getLeavingColumn(double[][] currentMatrix, double[] solution, double[] pattern){
        RealMatrix matrix = new Array2DRowRealMatrix(currentMatrix);
        RealVector vector = new ArrayRealVector(pattern);

        DecompositionSolver solver;
        double[] simplexDirection;
        solver = new LUDecomposition(matrix).getSolver();
        RealVector simplexDirectionVector = solver.solve(vector);
        simplexDirection = simplexDirectionVector.toArray();

        double currentBestValue = Double.POSITIVE_INFINITY;
        int leavingColumn = 0;

        for (int i = 0; i < simplexDirection.length; i++) {
            double currentValue = solution[i] / simplexDirection[i];
            if (simplexDirection[i] > Double.MIN_VALUE && solution[i] / simplexDirection[i] < currentBestValue && matrixIsNotSingular(currentMatrix, pattern, i)) {
                currentBestValue = currentValue;
                leavingColumn = i;
            }
        }

        return leavingColumn;
    }

    private static boolean matrixIsNotSingular(double[][] currentMatrix, double[] pattern, int leavingColumn){
        double[][] matrixCopy = new double[currentMatrix.length][currentMatrix[0].length];

        for (int i = 0; i < currentMatrix.length; i++) {
            System.arraycopy(currentMatrix[i], 0, matrixCopy[i], 0, currentMatrix[i].length);
        }

        for (int i = 0; i < matrixCopy[leavingColumn].length; i++) {
            matrixCopy[i][leavingColumn] = pattern[i];
        }

        RealMatrix matrix = MatrixUtils.createRealMatrix(matrixCopy);
        boolean isNotSingular = new LUDecomposition(matrix).getSolver().isNonSingular();
        return isNotSingular;
    }

    private static double[][] switchColumn(double[][] currentMatrix, int[] pattern, int leavingColumn){
        for (int i = 0; i < currentMatrix[leavingColumn].length; i++){
            currentMatrix[i][leavingColumn] = pattern[i];
        }
        return currentMatrix;
    }

    private static boolean solutionIsOptimal(double[] relativeCosts, double[] pattern){
        RealMatrix costsMatrix = MatrixUtils.createRowRealMatrix(relativeCosts);
        RealVector patternVector = MatrixUtils.createRealVector(pattern);
        RealVector result = costsMatrix.operate(patternVector);
        double finalValue = 1.0 - result.getEntry(0);
        return finalValue >= 0;
    }

    public static void main(String[] args) {
        Reel A = new Reel(0, 10, 9, 1);
        Reel B = new Reel(1, 20, 12, 1);
        Reel C = new Reel(2, 30, 5, 1);
        Reel D = new Reel(3, 40, 7, 1);

        ReelList reelList = new ReelList(0, 60, 30, 2);

        reelList.addReel(A);
        reelList.addReel(B);
        reelList.addReel(C);
        reelList.addReel(D);

        solve(reelList);
        SolutionCombination result = solve(reelList);
        int[] qtdPatterns = result.getSolutionQtdPatterns();
        int[][] patterns = result.getSolutionPatterns();
    }

}
