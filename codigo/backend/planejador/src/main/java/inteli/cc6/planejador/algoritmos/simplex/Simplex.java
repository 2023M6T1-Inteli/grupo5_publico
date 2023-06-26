package inteli.cc6.planejador.algoritmos.simplex;

import inteli.cc6.planejador.algoritmos.patterns.PatternList;
import inteli.cc6.planejador.algoritmos.reels.Reel;
import inteli.cc6.planejador.algoritmos.reels.ReelList;

import java.util.ArrayList;
import java.util.Arrays;

import static inteli.cc6.planejador.algoritmos.patternGenerators.Greedy.greedyPatternList;

public class Simplex {
    private double[][] tableau;
    private static final double EPSILON = 1e-10;

    // Constructor class
    public Simplex(double[][] tableau) {
        this.tableau = tableau;
    }

    /**
     * Function receives the mathematical model restraints and objective values to create
     * a tableau to run the simplex algorithm.
     *
     * @param patternsVector - 2D array with all possible patterns
     * @param demandVector - 1D array with the demand value of each reel
     * @param widthVector - 1D array with the width value of each reel
     * @param lMax - maximum size of the Jumbo reel
     * @param n_p - total number of patterns
     * @param n_b - total number of reels
     * @return tableau - completed tableau to run the simplex algorithm
     */
    public static double[][] createTableau(int[][] patternsVector, ArrayList<Integer> demandVector, ArrayList<Integer> widthVector, double lMax, int n_p, int n_b) {
        double[][] tableau = new double[n_b + 1][n_p + n_b + 1];
        for (int i = 0; i < n_p + n_b; i++){
            tableau[n_b][i] = lMax;
            if (i > n_p){
                tableau[n_b][i] = 0;
            }
        }
        for (int i = 0; i < n_b; i++){
            for (int j = 0; j < n_p; j++){
                tableau[i][j] = (double) patternsVector[j][i];
                // Objective function
                tableau[n_b][j] += widthVector.get(i) * patternsVector[j][i];
            }
            // Slack Variables
            tableau[i][n_p + i] = demandVector.get(i);

            // Demand
            tableau[i][n_p + n_b] = demandVector.get(i);
        }
        return tableau;
    }

    // Function to loop through the tableau and print all elements
    private static void printTableau(double[][] tableau) {
        for (double[] row : tableau) {
            for (double cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Main function that will loop until a solution is found,
     * passing through all the other functions in the class N times.
     *
     * @param tableau - completed tableau with restrictions and objective value
     */
    public static void solve(double[][] tableau) {
        int numRows = tableau.length;
        int numCols = tableau[0].length;

        // Loop until the simplex solution is found
        while (true) {
            int pivotColumn = findPivotColumn(tableau);
            if (pivotColumn == -1)
                break;  // Solution found
            int pivotRow = findPivotRow(tableau, pivotColumn);
            if (pivotRow == -1)
                throw new ArithmeticException("Unbounded solution");
            pivotElement(tableau, pivotRow, pivotColumn);
        }

        // Loop to get the basic variables of the solution and
        double[] solution = new double[numCols - 1];
        for (int i = 0; i < numRows - 1; i++) {
            int pivotColumn = findBasicVariableColumn(tableau, i);
            if (pivotColumn != -1)
                solution[pivotColumn - 1] = tableau[i][numCols - 1];
        }

        // Print of the solution array
        System.out.println("Variables:");
        System.out.println(Arrays.toString(solution));
    }

    /**
     * Function to find the pivot column of the tableau in it's current status
     *
     * @param tableau - current status of the tableau
     * @return pivotColumn - column to be removed from the basic variable list
     */
    private static int findPivotColumn(double[][] tableau) {
        int pivotColumn = -1;
        int numCols = tableau[0].length;

        for (int j = 0; j < numCols - 1; j++) {
            if (tableau[tableau.length - 1][j] > EPSILON) {
                if (pivotColumn == -1 || tableau[tableau.length - 1][j] < tableau[tableau.length - 1][pivotColumn])
                    pivotColumn = j;
            }
        }

        return pivotColumn;
    }

    /**
     * Function to find the pivot row of the tableau in it's current status
     *
     * @param tableau - current status of the tableau
     * @param pivotColumn - column that will be removed from the basic variable list
     * @return pivotRow - row variable to be added from the basic variable list
     */
    private static int findPivotRow(double[][] tableau, int pivotColumn) {
        int pivotRow = -1;
        int numRows = tableau.length;
        int numCols = tableau[0].length;
        double minRatio = Double.MAX_VALUE;

        for (int i = 0; i < numRows - 1; i++) {
            if (tableau[i][pivotColumn] > EPSILON) {
                double ratio = tableau[i][numCols - 1] / tableau[i][pivotColumn];
                if (ratio < minRatio) {
                    minRatio = ratio;
                    pivotRow = i;
                }
            }
        }

        return pivotRow;
    }

    /**
     * Function to update the tableau,
     * removing and adding the pivot variables and inserting new ones.
     *
     * @param tableau - current status of the tableau
     * @param pivotRow - row variable that will be added to the basic variable list
     * @param pivotColumn - column that will be removed from the basic variable list
     */
    private static void pivotElement(double[][] tableau, int pivotRow, int pivotColumn) {
        int numRows = tableau.length;
        int numCols = tableau[0].length;
        double pivot = tableau[pivotRow][pivotColumn];

        for (int j = 0; j < numCols; j++) {
            tableau[pivotRow][j] /= pivot;
        }

        for (int i = 0; i < numRows; i++) {
            if (i != pivotRow) {
                double factor = tableau[i][pivotColumn];
                for (int j = 0; j < numCols; j++) {
                    tableau[i][j] -= factor * tableau[pivotRow][j];
                }
            }
        }
    }

    /**
     * Function to be called n_p + n_b times, to get the basic variable, if any, of each column.
     *
     * @param tableau - current status of the tableau
     * @param pivotRow - current basic variable
     * @return - returns the basic variable from the current column or "-1" if none were found
     */
    private static int findBasicVariableColumn(double[][] tableau, int pivotRow) {
        int numCols = tableau[0].length;

        for (int j = 0; j < numCols - 1; j++) {
            int count = 0;
            int idx = -1;

            for (int i = 0; i < tableau.length - 1; i++) {
                if (Math.abs(tableau[i][j] - 1.0) < EPSILON) {
                    count++;
                    idx = i;
                }
            }

            if (count == 1 && idx == pivotRow)
                return j + 1;
        }

        return -1;
    }

    // Function main to test algorithm
    public static void main(String[] args) {
        // Creating test reels
        Reel A = new Reel(0, 10, 1,1);
        Reel B = new Reel(1, 20, 1,1);
        Reel C = new Reel(2, 30, 1,1);
        Reel D = new Reel(3, 40, 1,1);
        Reel E = new Reel(4, 50, 1,1);
        Reel F = new Reel(5, 60, 1,1);
        Reel G = new Reel(6, 70, 1,1);
        Reel H = new Reel(7, 15, 1,1);
        Reel I = new Reel(8, 25, 1,1);
        Reel J = new Reel(9, 35, 1,1);
        Reel K = new Reel(10, 30, 1,1);
        Reel L = new Reel(11, 55, 1,1);
        Reel M = new Reel(12, 25, 1,1);
        Reel N = new Reel(13, 45, 1,1);
        Reel O = new Reel(14, 40, 1,1);
        Reel P = new Reel(15, 100, 1,1);

        // Adding the reels to reelList class
        ReelList reelList = new ReelList(0, 100, 80, 4);
        reelList.addReel(A);
        reelList.addReel(B);
        reelList.addReel(C);
        reelList.addReel(D);
        reelList.addReel(E);
        reelList.addReel(F);
        reelList.addReel(G);
        reelList.addReel(H);
        reelList.addReel(I);
        reelList.addReel(J);
        reelList.addReel(K);
        reelList.addReel(L);
        reelList.addReel(M);
        reelList.addReel(N);
        reelList.addReel(O);
        reelList.addReel(P);

        // Generating patterns with the greedy algorithm
        PatternList patternList = greedyPatternList(reelList, 100, 4);

        // Matrix with all patterns generated
        int[][] patternsVector = new int[patternList.getPatternList().size()][reelList.getReelList().size()];
        for (int i = 0; i < patternList.getPatternList().size(); i++){
            patternsVector[i] = patternList.getPatternList().get(i).getPatternVector(reelList.getReelList().size());
        }

        // Vectors with reel widths and demands
        ArrayList<Integer> widthVector = reelList.getWidthsList();
        ArrayList<Integer> demandVector = reelList.getDemandList();

        // Reels widths, demands and the size of the Jumbo
        int n_b = reelList.getReelList().size();
        int n_p = patternsVector.length;
        int lMax = 100;

        // Creation of the simplex tableau
        double[][] tableau = createTableau(patternsVector, demandVector, widthVector, lMax, n_p, n_b);

        // Creation of the simplex algorithm class and solution to the problem
        Simplex simplex = new Simplex(tableau);
        printTableau(simplex.tableau);
        solve(tableau);

    }
}