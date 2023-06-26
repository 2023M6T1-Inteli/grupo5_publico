package inteli.cc6.planejador.algoritmos.patterns;

import java.util.ArrayList;

import inteli.cc6.planejador.algoritmos.reels.Reel;

public class Pattern {

    private final ArrayList<Reel> reels = new ArrayList<Reel>();
    private int patternWidth;
    private final int patternId;

    // @param patternId - Int to be stored as this pattern's identification
    public Pattern(int patternId) {
        this.patternId = patternId;
        this.patternWidth = 0;
    }

    // @param reel - Reel to be added to this pattern's Reels' ArrayList
    public void addPatternReel(Reel reel) {
        this.reels.add(reel);
        this.patternWidth += reel.getReelWidth();
    }

    // @return reels - ArrayList containing all reels stored in this class
    public ArrayList<Reel> getPatternReels() {
        return this.reels;
    }

    // @return patternWidth - Integer containing the sum of the withs of all reels
    // of this pattern
    public int getPatternWidth() {
        return this.patternWidth;
    }

    // @return patternId - this pattern's identification
    public int getPatternId() {
        return this.patternId;
    }

    public int[] getPatternVector(int reelAmmount) {

        int[] patternVector = new int[reelAmmount];

        for (Reel reel : this.reels) {
            patternVector[reel.getReelId()] += 1;
        }

        return patternVector;
    }

    public double[] getPatternVectorDouble(int reelAmmount) {

        double[] patternVector = new double[reelAmmount];

        for (Reel reel : this.reels) {
            patternVector[reel.getReelId()] += 1;
        }

        return patternVector;
    }

    @Override
    public String toString() {
        return "Pattern [reels=" + reels + ", patternWidth=" + patternWidth + ", patternId=" + patternId + "]";
    }

    public double getPatternPi(double[] piVector) {

        double pi = 0;

        for (int i = 0; i < reels.size(); i++) {
            pi += piVector[reels.get(i).getReelId()];
        }

        return pi;
    }
}
