package inteli.cc6.planejador.algoritmos.newPatternGen;

import java.util.ArrayList;
import java.util.Arrays;

import inteli.cc6.planejador.algoritmos.patterns.Pattern;
import inteli.cc6.planejador.algoritmos.reels.Reel;
import inteli.cc6.planejador.algoritmos.reels.ReelList;

public class PatternGenerator {

  private ReelList reelList;// The list of reels used for pattern generation
  double[][] optimalsGlobal;// 2D array to store the optimal values for each width and column

  /**
   * Constructs a PatternGenerator object with the specified reel list.
   * 
   * @param reelList the reel list to use for pattern generation
   */
  public PatternGenerator(ReelList reelList) {
    this.reelList = reelList;
  }

  /**
   * Generates a new pattern based on the given pi vector.
   * 
   * @param piVector the pi vector used for generating the pattern
   * @return the generated pattern
   */
  public Pattern newPattern(double[] piVector) {

    double[][] optimals = new double[reelList.getReelListJumboSizeMax() + 1][reelList.getReelList().size() + 1];
    // 2D array to store the optimal values for each width and column

    optimalsGlobal = optimals;

    for (int j = 0; j < reelList.getReelList().size() + 1; j++) {
      for (int i = 0; i < optimals.length; i++) {
        optimals[i][j] = optimal(i, j, optimals, piVector);// Calculates and stores the optimal value for each width
                                                           // and column
      }
    }

    ArrayList<Reel> patternReels = new ArrayList<Reel>(); // List to store the reels that form the generated pattern

    int capacity = optimals.length - 1; // Remaining capacity of the pattern
    int col = reelList.getReelList().size();// Current column index

    while (optimals[capacity][col] > 0.0) {
      // Traverse through the optimal values to determine the reels in the pattern
      if (optimals[capacity][col] != optimals[capacity][col - 1]) {
        patternReels.add(reelList.getReelList().get(col - 1));// Add the reel at the current column index to the pattern
        capacity -= reelList.getWidthsList().get(col - 1);// Reduce the remaining capacity by the width of the added
                                                          // reel
      } else
        col -= 1;// Move to the previous column index if the optimal value is the same as the one
                 // in the previous column
    }

    Pattern pattern = new Pattern(0);// Create a new pattern object

    // Add each reel from the patternReels list to the pattern object
    for (Reel r : patternReels) {
      pattern.addPatternReel(r);
    }

    return pattern;
  }

  /**
   * function that return the best pi value with the pivector, the optimals 2D
   * array and the position (row and column) of the array
   * 
   * @param width    row of optimals, represent the capacity
   * @param col      col of optimals, represent the amount of reels that is
   *                 being analyzed, based in the index of the reel list
   * @param optimals the 2D array containing the best value possible given a
   *                 capacity of the jumbo roll and the index of the reel
   * @param piVector array containing the pi value of each reel
   * @return the best value to be added to optimals
   */
  private double optimal(int width, int col, double[][] optimals, double[] piVector) {
    if (optimals[width][col] != 0)
      return optimals[width][col];// If the optimal value is already calculated, return it

    if (col == 0)
      return 0;// Base case: If we reach column index 0, return 0 as there are no more reels to
               // consider

    int reel = col - 1;// Get the index of the reel in the reel list for the current column

    // If the width of the reel is greater than the current width, skip adding the
    // reel and move to the previous column to find the optimal value
    if (reelList.getWidthsList().get(reel) > width)
      return optimals[width][col - 1];

    // Calculate the optimal value for the current width and column by considering
    // two cases:
    // 1. Adding the current reel's value (piVector[reel]) and the optimal value for
    // the remaining width and previous column
    // 2. Not adding the current reel and considering the optimal value for the
    // remaining width and previous column
    return Math.max(piVector[reel] + optimals[width - reelList.getReelList().get(reel).getReelWidth()][col],
        optimals[width][col - 1]);

  }

  // tests
  public static void main(String[] args) {
    Reel A = new Reel(0, 10, 1, 1);
    Reel B = new Reel(1, 20, 1, 1);
    Reel C = new Reel(2, 30, 1, 1);
    Reel D = new Reel(3, 40, 1, 1);

    ReelList reelList = new ReelList(0, 70, 30, 5);

    reelList.addReel(A);
    reelList.addReel(B);
    reelList.addReel(C);
    reelList.addReel(D);

    double[] piVector = { 0.9, 1.0, 2.5, 30.0 };

    PatternGenerator pattGen = new PatternGenerator(reelList);

    double bestValue = pattGen.newPattern(piVector).getPatternPi(piVector);

    for (int i = 0; i < pattGen.optimalsGlobal.length; i++) {
      System.out.println(i + " " + Arrays.toString(pattGen.optimalsGlobal[i]));
    }

  }

}
