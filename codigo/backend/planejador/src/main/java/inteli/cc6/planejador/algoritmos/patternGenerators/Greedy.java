package inteli.cc6.planejador.algoritmos.patternGenerators;

import inteli.cc6.planejador.algoritmos.patterns.Pattern;
import inteli.cc6.planejador.algoritmos.patterns.PatternList;
import inteli.cc6.planejador.algoritmos.reels.Reel;
import inteli.cc6.planejador.algoritmos.reels.ReelList;

import java.util.ArrayList;
import java.util.Collections;

public class Greedy {

    /**
     * Function used to generate patterns, with a greedy algorithm, given a list of reels.
     * It generates X paternLists and returns the best one.
     *
     * @param reelList - list with all demanded reels
     * @param jumboSize - integer with the Jumbo size value in millimeters
     * @param knives - ammount of knives in the machine, used to limit the ammount of reels in each pattern
     * @return patternList - best entity of class paternList generated
     */
    public static PatternList greedyPatternList(ReelList reelList, int jumboSize, int knives){

        // ArrayList to keep all of the generated paternLists, width is used to get the best one
        ArrayList<PatternList> patternLists = new ArrayList<PatternList>();
        int[] width = new int[50000];

        // Initial loop to fill the width with values and the array with patter lists
        for (int i = 0; i < width.length; i++){
            PatternList patternList = new PatternList(); // Creation of a new patterList to fill
            Collections.shuffle(reelList.getReelList()); // Shuffling the list of reels to get new patterns
            int patternIndex = 0;

            // Secondary loop to pass through the reelList more times, and get more patterns to each patterList
            for (int j = 0; j < reelList.getReelList().size(); j++){
                Collections.shuffle(reelList.getReelList());

                // Third loop to go through the whole reelList generating patterns
                for (int z = 0; z < reelList.getReelList().size();){
                    Pattern currentPattern = new Pattern(patternIndex);

                    // Final loop to add reels to "currentPattern" until the limit is exceeded
                    while (z < reelList.getReelList().size() && currentPattern.getPatternWidth() + reelList.getReelList().get(z).getReelWidth() <= jumboSize && currentPattern.getPatternReels().size() <= knives){
                        if (currentPattern.getPatternReels().size() == 0){
                            currentPattern.addPatternReel(reelList.getReelList().get(z));
                            width[i] += reelList.getReelList().get(z).getReelWidth();
                        }
                        else if (currentPattern.getPatternReels().get(currentPattern.getPatternReels().size() - 1).getReelWidth() >= reelList.getReelList().get(z).getReelWidth()){
                            currentPattern.addPatternReel(reelList.getReelList().get(z));
                            width[i] += reelList.getReelList().get(z).getReelWidth();
                        }
                        z++;
                    }
                    patternIndex ++;
                    patternList.addPattern(currentPattern);
                }
                patternLists.add(patternList);
            }
        }

        // Verifying which index of the array ~width~ has the best value to return the best paterList created
        int currentWidth = 0;
        for (int i = 0; i <= width.length - 1; i++){
            if (width[i] > width[currentWidth]){
                currentWidth = i;
            }
        }

        return patternLists.get(currentWidth);
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

        // Function to create a patter list
        PatternList patternList = greedyPatternList(reelList, 100, 4);

        // Print of each generated pattern in the pattern list
        for (int i = 0; i < patternList.getPatternList().size(); i++){
            for (int j = 0; j <= patternList.getPatternList().get(i).getPatternReels().size() - 1; j++){
                System.out.println(patternList.getPatternList().get(i).getPatternReels().get(j).getReelWidth());
            }
            System.out.println("--------------------------------------------");
        }
    }
}