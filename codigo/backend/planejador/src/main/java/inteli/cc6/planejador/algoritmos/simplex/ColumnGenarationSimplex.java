package inteli.cc6.planejador.algoritmos.simplex;

import java.util.ArrayList;
import java.util.Arrays;

import inteli.cc6.planejador.algoritmos.newPatternGen.BranchAndBoundPatternGenerator;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;

import inteli.cc6.planejador.algoritmos.reels.ReelList;
import inteli.cc6.planejador.algoritmos.results.SolutionCombination;
import inteli.cc6.planejador.algoritmos.patterns.Pattern;
import inteli.cc6.planejador.algoritmos.newPatternGen.PatternGenerator;

/**
 * This class implements the column generation algorithm with simplex for
 * solving a problem related to reel lists.
 */
public class ColumnGenarationSimplex {

    /**
     * Applies the column generation algorithm with simplex to a reel list.
     *
     * @param reelList The reel list to be considered.
     * @return An instance of SolutionCombination containing the generated solution.
     */
    public SolutionCombination columnGenerationSimplex(ReelList reelList) {
        // Widths and demands of the reels
        ArrayList<Integer> widthVector = reelList.getWidthsList();
        ArrayList<Integer> demandVector = reelList.getDemandList();

        return columnGenerationSimplexAux(reelList, widthVector, demandVector);
    }

    /**
     * Applies the column generation algorithm with simplex to a list of reels.
     *
     * @param reelList     The list of reels to be considered.
     * @param widthVector  The vector containing the widths of the reels.
     * @param demandVector The vector containing the demands of the reels.
     * @return An instance of SolutionCombination containing the generated solution.
     */
    private static SolutionCombination columnGenerationSimplexAux(ReelList reelList, ArrayList<Integer> widthVector,
        ArrayList<Integer> demandVector) {
        
        int nb = reelList.getReelList().size();

        double[][] patternMatrixAux = new double[nb][nb];

        patternMatrixAux = identityMatrix(reelList.getReelListJumboSizeMax(), widthVector, patternMatrixAux);

        double[] cbMatrix = new double[nb];

        Arrays.fill(cbMatrix, 1);

        Pattern pattern;

        // Need to increment the ID in the reel list generator (++ID) for the next ID
        PatternGenerator patternGenerated = new PatternGenerator(reelList);

        double[] solutions;

        double[] piVector;

        double[] patternVector = new double[nb];

        double[] simplexDirection;

        int tradePosition;

        while (true) {
            
            long passoStartTime = System.nanoTime();

            // Step 1
            solutions = generateHomogeneousMatrix(demandVector, patternMatrixAux);

            // Step 2
            piVector = findNewBaseColumn(patternMatrixAux, cbMatrix);

            // Step 3
            //pattern = patternGenerated.newPattern(piVector);
            pattern = BranchAndBoundPatternGenerator.generateBranchAndBoundPattern(piVector, reelList);

            int[] patternVectorInt = pattern.getPatternVector(nb);

            for (int i = 0; i < patternVectorInt.length; i++) {
                patternVector[i] = patternVectorInt[i];

            }

            // Step 4
            if (optimalityTest(piVector, patternVector )) {
                return new SolutionCombination(reelList, solutions, patternMatrixAux);
            }

            // Step 5
            simplexDirection = whichColumnLeaves(patternMatrixAux, patternVector);

            // Step 6
            tradePosition = determineColumn(solutions, simplexDirection);

            // Step 7
            tradeColumn(tradePosition, patternMatrixAux, patternVector);


        }
    }

    /**
     * Generates an identity matrix based on the jumbo size and width vector.
     *
     * @param jumboSize        The maximum jumbo size.
     * @param widthVector      The vector containing the widths of the reels.
     * @param patternMatrixAux The auxiliary pattern matrix.
     * @return The generated identity matrix.
     */
    public static double[][] identityMatrix(int jumboSize, ArrayList<Integer> widthVector,
            double[][] patternMatrixAux) {
        int n = patternMatrixAux.length;

        // Create the pattern matrix from jumboSize and widthVector
        for (int i = 0, j = 0; i < n; i++, j++) {
            double value = jumboSize / widthVector.get(i).doubleValue();
            patternMatrixAux[i][j] = Math.floor(value);
        }

        return patternMatrixAux;
    }

    /**
     * Generates the homogeneous matrix using the demand vector and pattern matrix.
     *
     * @param demandVector     The vector containing the demands of the reels.
     * @param patternMatrixAux The auxiliary pattern matrix.
     * @return The generated homogeneous matrix.
     */
    public static double[] generateHomogeneousMatrix(ArrayList<Integer> demandVector, double[][] patternMatrixAux) {
        RealMatrix matrix = new Array2DRowRealMatrix(patternMatrixAux);
        RealVector vector = new ArrayRealVector(demandVector.stream().mapToDouble(Integer::doubleValue).toArray());

        DecompositionSolver solver;
        double[] solution = null;
        try {
            solver = new LUDecomposition(matrix).getSolver();
            RealVector solutionVector = solver.solve(vector);
            solution = solutionVector.toArray();
        } catch (SingularMatrixException e) {
            // Tratar caso em que a matriz é singular (não possui solução)
            // Aqui você pode lançar uma exceção, retornar null ou qualquer tratamento
            // adequado
            System.err.println("A matriz é singular. Não é possível obter uma solução.1");
        }

            return solution;
    }

    /**
     * Finds the new base column using the pattern matrix and cb matrix.
     *
     * @param patternMatrixAux The auxiliary pattern matrix.
     * @param cbMatrix         The cb matrix.
     * @return The new base column.
     */
    private static double[] findNewBaseColumn(double[][] patternMatrixAux, double[] cbMatrix) {
        RealMatrix matrixTranspose = new Array2DRowRealMatrix(patternMatrixAux).transpose();
        RealVector vector = new ArrayRealVector(cbMatrix);

        DecompositionSolver solver;
        double[] piVector = null;
        try {
            solver = new LUDecomposition(matrixTranspose).getSolver();
            RealVector piVectorVector = solver.solve(vector);
            piVector = piVectorVector.toArray();
        } catch (SingularMatrixException e) {
            // Tratar caso em que a matriz é singular (não possui solução)
            // Aqui você pode lançar uma exceção, retornar null ou qualquer tratamento
            // adequado
            System.err.println("A matriz é singular. Não é possível obter uma solução.2");
        }

        return piVector;
    }

    /**
     * Performs the optimality test based on the pi vector and pattern vector.
     *
     * @param piVector      The pi vector.
     * @param patternVector The pattern vector.
     * @return True if the optimality test passes, False otherwise.
     */
    private static boolean optimalityTest(double[] piVector, double[] patternVector) {

        // Construct matrix A and vector B from the input data
        RealMatrix matrixA = MatrixUtils.createRowRealMatrix(piVector);
        RealVector vectorB = MatrixUtils.createRealVector(patternVector);

        // Calculate the result of A * B
        RealVector result = matrixA.operate(vectorB);

        // Calculate estimationCn
        double estimationCn = 1.0 - result.getEntry(0);


        // Check if estimationCn is greater than or equal to 0 and return the
        // corresponding result
        return estimationCn >= 0;
    }

    /**
     * Calculates the column that leaves the basis based on the pattern matrix and
     * pattern vector.
     *
     * @param patternMatrixAux The auxiliary pattern matrix.
     * @param patternVector    The pattern vector.
     * @return The column that leaves the basis.
     */
    private static double[] whichColumnLeaves(double[][] patternMatrixAux, double[] patternVector) {
        RealMatrix matrix = new Array2DRowRealMatrix(patternMatrixAux);
        RealVector vector = new ArrayRealVector(patternVector);

        DecompositionSolver solver;
        double[] simplexDirection = null;
        try {
            solver = new LUDecomposition(matrix).getSolver();
            RealVector simplexDirectionVector = solver.solve(vector);
            simplexDirection = simplexDirectionVector.toArray();
        } catch (SingularMatrixException e) {
            // Tratar caso em que a matriz é singular (não possui solução)
            // Aqui você pode lançar uma exceção, retornar null ou qualquer tratamento
            // adequado
            System.err.println("A matriz é singular. Não é possível obter uma solução.3");
        }

        return simplexDirection;
    }

    /**
     * Determines the column to be traded based on the simplex direction and current
     * solution.
     *
     * @param simplexDirection The simplex direction.
     * @param solution         The current solution.
     * @return The position of the column to be traded.
     */
    private static int determineColumn(double[] simplexDirection, double[] solution) {
        double currentR;
        double bestR = Double.NEGATIVE_INFINITY;
        int position = 0;

        for (int i = 0; i < simplexDirection.length; i++) {
            currentR = solution[i] / simplexDirection[i];

            if (currentR >= bestR) {
                bestR = currentR;
                position = i;
            }
        }

        return position;
    }

    /**
     * Trades the column at the given position in the pattern matrix with the
     * pattern vector.
     *
     * @param position         The position of the column to be traded.
     * @param patternMatrixAux The auxiliary pattern matrix.
     * @param patternVector    The pattern vector.
     */
    private static void tradeColumn(int position, double[][] patternMatrixAux, double[] patternVector) {
        for (int i = 0; i < patternVector.length; i++) {
            patternMatrixAux[i][position] = patternVector[i];
        }

    }

}
