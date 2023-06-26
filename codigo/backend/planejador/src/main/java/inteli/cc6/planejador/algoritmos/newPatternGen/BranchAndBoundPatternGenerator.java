package inteli.cc6.planejador.algoritmos.newPatternGen;

import inteli.cc6.planejador.algoritmos.patterns.Pattern;
import inteli.cc6.planejador.algoritmos.reels.Reel;
import inteli.cc6.planejador.algoritmos.reels.ReelList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BranchAndBoundPatternGenerator {

    public static Pattern generateBranchAndBoundPattern(double[] piVector, ReelList reelList){

        int knives = reelList.getReelListKnivesQtd();
        int jumboSize = reelList.getReelListJumboSizeMax();
        int minSize = reelList.getReelListJumboSizeMin();
        ArrayList<Integer> reelListWidthVector = reelList.getWidthsList();
        int[] bestPattern = new int[piVector.length];
        Arrays. fill(bestPattern, 0);
        int[][] sortedValues = getSortedValues(reelListWidthVector, bestPattern);
        bestPattern = branchAndBound(bestPattern, piVector, 0, jumboSize, reelListWidthVector, knives, sortedValues, minSize);
        Pattern pattern = new Pattern(0);
        for (int i = 0; i < bestPattern.length; i++){
            if (bestPattern[i] > 0){
                for (int j = 0; j < bestPattern[i]; j++){
                    pattern.addPatternReel(reelList.getReelList().get(i));
                }
            }
        }
        currentBestValue = Double.MIN_VALUE;
        return pattern;

    }

    static double currentBestValue = Double.MIN_VALUE;
    static int[] CurrentBestPattern;

    private static int[] branchAndBound(int[] currentPattern, double[] piVector, int currentBranch, int jumboSize, ArrayList<Integer> reelListWidthVector, int knives, int[][] sortedValues, int minSize) {

        if (currentBranch < currentPattern.length){
            for (int i = knives; i >= 0; i--){
                currentPattern[sortedValues[currentBranch][0]] = i;
                int currentSize = getCurrentSize(currentPattern, reelListWidthVector);
                if (currentSize > jumboSize){
                    continue;
                }
                double[] values = getCurrentValue(currentPattern, piVector);
                if (values[1] > knives){
                    continue;
                }
                int[] result = branchAndBound(currentPattern, piVector, currentBranch + 1, jumboSize, reelListWidthVector, knives, sortedValues, minSize);
                if (values[0] > currentBestValue && currentSize > minSize){
                    currentBestValue = values[0];
                    CurrentBestPattern = new int[currentPattern.length];
                    System.arraycopy(currentPattern, 0, CurrentBestPattern, 0, currentPattern.length);
                }
            }
        }
        return CurrentBestPattern;
    }

    private static double[] getCurrentValue(int[] currentPattern, double[] piVector){
        double[] values = new double[2];
        for (int i = 0; i < currentPattern.length; i++){
            if (currentPattern[i] > 0){
                values[0] += piVector[i] * currentPattern[i];
                values[1] += currentPattern[i];
            }
        }
        return values;
    }

    private static int[][] getSortedValues(ArrayList<Integer> reelListWidthVector, int[] solution){
        int[][] sortedWidths = new int[solution.length][2];

        for (int i = 0; i < reelListWidthVector.size(); i++){
            sortedWidths[i][0] = i;
            sortedWidths[i][1] = reelListWidthVector.get(i);
        }

        Arrays.sort(sortedWidths, Comparator.comparingDouble(o -> o[1]));
        int[][] newSortedWidths = new int[solution.length][2];

        for (int i = reelListWidthVector.size() - 1; i >= 0; i--){
            newSortedWidths[i][0] = reelListWidthVector.size() - 1 - i;
            newSortedWidths[i][1] = reelListWidthVector.get(reelListWidthVector.size() - 1 - i);
        }

        return newSortedWidths;
    }

    private static int getCurrentSize(int[] currentPattern, ArrayList<Integer> reelListWidthVector){
        int currentSize = 0;
        for (int i = 0; i < currentPattern.length; i++){
            currentSize += currentPattern[i] * reelListWidthVector.get(i);
        }
        return currentSize;
    }

    public static void main(String[] args) {
        Reel A = new Reel(0, 10, 1, 1);
        Reel B = new Reel(1, 20, 1, 1);
        Reel C = new Reel(2, 30, 1, 1);
        Reel D = new Reel(3, 40, 1, 1);

        ReelList reelList = new ReelList(0, 60, 30, 2);

        reelList.addReel(A);
        reelList.addReel(B);
        reelList.addReel(C);
        reelList.addReel(D);

        double[] piVector = { 0.9, 1.0, 2.5, 30.0 };

        int knives = reelList.getReelListKnivesQtd();
        int jumboSize = reelList.getReelListJumboSizeMax();
        int minSize = reelList.getReelListJumboSizeMin();
        ArrayList<Integer> reelListWidthVector = reelList.getWidthsList();
        int[] bestPattern = new int[piVector.length];
        Arrays. fill(bestPattern, -1);
        int[][] sortedValues = getSortedValues(reelListWidthVector, bestPattern);
        int[] patternGenerated = branchAndBound(bestPattern, piVector, 0, jumboSize, reelListWidthVector, knives, sortedValues, minSize);

        for (int i = 0; i < patternGenerated.length; i++) {
            System.out.println(i + " " + patternGenerated[i]);
        }

    }
}
