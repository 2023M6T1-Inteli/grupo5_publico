package inteli.cc6.planejador.algoritmos.patternGenerators;

import inteli.cc6.planejador.algoritmos.reels.Reel;
import inteli.cc6.planejador.algoritmos.reels.ReelList;

import java.util.ArrayList;

public class Dynamic {

    /**
     * Function used to generate patterns, with a Dynamic algorithm, given a list of reels.
     * It generates a great solution comparing the values of the width and returns the best solution,
     * with the lowest waste (difference between the jumboSize and the withs of the reels added).
     *
     * @param reelList - list with all demanded reels
     * @param jumboSize - integer with the Jumbo size value in millimeters
     * @return maxCapacityBag - matrix containing all the widths in a Dynamic table
     */
    public static int[][] DynamicPatternList(ReelList reelList, int jumboSize) {
        int reelListSize = reelList.getReelList().size();
        int[][] maxCapacityBag = new int[reelListSize + 1][jumboSize + 1];

        //Makes the table of the maxCapacity of each millimeter of the jumboSize
        for (int i = 1; i <= reelListSize; i++){
            for (int j = 1; j <= jumboSize; j++){
                if (reelList.getReelList().get(i -1).getReelWidth() > j){
                   maxCapacityBag[i][j] = maxCapacityBag[i-1][j];
                }
                else{
                    int previusReelWidth = reelList.getReelList().get(i-1).getReelWidth();
                    maxCapacityBag[i][j] = Math.max(maxCapacityBag[i-1][j], previusReelWidth + maxCapacityBag[i-1][j - previusReelWidth]);

                }
            }
        }

        ArrayList<Integer> selected = new ArrayList<>();
        int column = reelListSize;
        int line = jumboSize;

        //Put the selected reels that fit into the jumboSize in a array
        while (column > 0 && line > 0) {
            if (maxCapacityBag[column][line] != maxCapacityBag[column - 1][line]) {
                selected.add(column - 1);
                line -= reelList.getReelList().get(column -1).getReelWidth();
            }
            column--;
        }

        //Print the selected items
        System.out.print("Solução ótima: ");
        for (int k = selected.size() - 1; k >= 0; k--) {
            System.out.print(selected.get(k) + "  ");
        }
        System.out.println("  ");

        return maxCapacityBag;
    }

    public static void main(String[] args) {
        //Mock data to test the Dynamic Algorithm
        Reel A = new Reel(0, 10, 1,1);
        Reel B = new Reel(1, 20, 1,1);
        Reel C = new Reel(2, 30, 1,1);
        Reel D = new Reel(3, 40, 1,1);
        Reel E = new Reel(4, 50, 1,1);
        Reel F = new Reel(5, 60, 1,1);

        //Ading the reels to reelList class
        ReelList reelList = new ReelList(0, 100, 80, 4);
        reelList.addReel(A);
        reelList.addReel(B);
        reelList.addReel(C);
        reelList.addReel(D);
        reelList.addReel(E);
        reelList.addReel(F);

        //Creates a test given a jumboSize of 50 millimeters and the reels created  above
        int[][] test = DynamicPatternList(reelList, 50);

        //Print the matrix maxCapacityBag with the value given in the int[] test
        for(int i = 0; i < test.length; i++){
            for(int j = 0; j < test[i].length; j++){
                System.out.print(test[i][j] + "  ");
            }
            System.out.println();
        }
    }
}