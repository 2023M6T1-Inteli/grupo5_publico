package inteli.cc6.planejador.algoritmos.results;

import inteli.cc6.planejador.algoritmos.reels.ReelList;

/**
 * Represents a combination solution containing patterns and quantities generated by the algorithm.
 */
public class SolutionCombination {
    private final int solutionId;
    private final ReelList solutionReelList;
    private final double[] solutionQtdPatterns;
    private final double[][] solutionPatterns;

    /**
     * Constructs a SolutionCombination object.
     *
     * @param reelList      The reel list used to generate the solution.
     * @param qtdPatterns   The quantities of each pattern in the solution.
     * @param patterns      The patterns included in the solution.
     */
    public SolutionCombination(ReelList reelList, double[] qtdPatterns, double[][] patterns) {
        this.solutionId = reelList.getReelListId();
        this.solutionReelList = reelList;
        this.solutionQtdPatterns = qtdPatterns;
        this.solutionPatterns = patterns;
    }

    /**
     * Gets the ID of the solution.
     *
     * @return The ID of the solution.
     */
    public int getSolutionId() {
        return this.solutionId;
    }

    /**
     * Gets the reel list used in the solution.
     *
     * @return The reel list used in the solution.
     */
    public ReelList getSolutionReelList() {
        return this.solutionReelList;
    }

    /**
     * Gets the quantities of each pattern in the solution.
     *
     * @return The quantities of each pattern in the solution.
     */
    public int[] getSolutionQtdPatterns() {
        int[] resultsInt = new int[solutionQtdPatterns.length];
        for (int i = 0; i < solutionQtdPatterns.length; i++) {
            resultsInt[i] = (int) Math.ceil(solutionQtdPatterns[i]);
        }
        return resultsInt;
    }

    /**
     * Gets the patterns included in the solution.
     *
     * @return The patterns included in the solution.
     */
    public int[][] getSolutionPatterns() {
        int[][] resultsInt = new int[solutionPatterns.length][solutionPatterns[0].length];
        for (int i = 0; i < solutionPatterns.length; i++) {
            for (int j = 0; j < solutionPatterns.length; j++) {
                resultsInt[i][j] = (int) Math.ceil(solutionPatterns[i][j]);
            }
        }
        return resultsInt;
    }

    public int getQtdReelProduced(int id){
        int reelProduced = 0;
        int[][] patterns = getSolutionPatterns();
        int[] qtdPatterns = getSolutionQtdPatterns();
        for(int i = 0; i< patterns[0].length; i++){
            reelProduced += patterns[id][i] * qtdPatterns[i];
        }
        return reelProduced;
    }

    /**
     * Prints the patterns included in the solution.
     */
    public void printSolutionPatterns() {
        for (int i = 0; i < this.solutionPatterns.length; i++) {
            for (int j = 0; j < this.solutionPatterns.length; j++) {
                System.out.println(this.solutionPatterns[i][j]);
            }
        }
    }
}
