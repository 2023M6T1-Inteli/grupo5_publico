package inteli.cc6.planejador.algoritmos.reels;

import java.util.ArrayList;

/**
 * Represents a list of reels.
 */
public class ReelList {
    private final int reelListId;
    private final ArrayList<Reel> reelList = new ArrayList<Reel>();
    private final ArrayList<Integer> demand = new ArrayList<Integer>();
    private final ArrayList<Integer> widths = new ArrayList<Integer>();
    private final int reelListJumboSizeMax;
    private final int reelListJumboSizeMin;
    private final int reelListKnivesQtd;

    /**
     * Constructs a ReelList object.
     *
     * @param id                The ID of the reel list.
     * @param jumboSizeMax      The maximum jumbo size of the reels in the list.
     * @param jumboSizeMin      The minimum jumbo size of the reels in the list.
     * @param knivesQtd         The quantity of knives for the reels in the list.
     */
    public ReelList(int id, int jumboSizeMax, int jumboSizeMin, int knivesQtd) {
        this.reelListId = id;
        this.reelListJumboSizeMax = jumboSizeMax;
        this.reelListJumboSizeMin = jumboSizeMin;
        this.reelListKnivesQtd = knivesQtd;
    }

    /**
     * Adds a reel to the reel list.
     *
     * @param reel      The reel to be added to the list.
     */
    public void addReel(Reel reel) {
        this.reelList.add(reel);
        this.demand.add(reel.getReelDemand());
        this.widths.add(reel.getReelWidth());
    }

    /**
     * Gets the reel list.
     *
     * @return The reel list containing all the reels.
     */
    public ArrayList<Reel> getReelList() {
        return this.reelList;
    }

    /**
     * Gets the demand list of the reels.
     *
     * @return The demand list of the reels.
     */
    public ArrayList<Integer> getDemandList() {
        return this.demand;
    }

    /**
     * Gets the widths list of the reels.
     *
     * @return The widths list of the reels.
     */
    public ArrayList<Integer> getWidthsList() {
        return this.widths;
    }

    /**
     * Gets the ID of the reel list.
     *
     * @return The ID of the reel list.
     */
    public int getReelListId() {
        return this.reelListId;
    }

    /**
     * Gets the maximum jumbo size of the reels in the list.
     *
     * @return The maximum jumbo size of the reels.
     */
    public int getReelListJumboSizeMax() {
        return this.reelListJumboSizeMax;
    }

    /**
     * Gets the minimum jumbo size of the reels in the list.
     *
     * @return The minimum jumbo size of the reels.
     */
    public int getReelListJumboSizeMin() {
        return this.reelListJumboSizeMin;
    }

    /**
     * Gets the quantity of knives for the reels in the list.
     *
     * @return The quantity of knives for the reels.
     */
    public int getReelListKnivesQtd() {
        return this.reelListKnivesQtd;
    }
}
