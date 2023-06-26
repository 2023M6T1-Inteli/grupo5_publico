package inteli.cc6.planejador.algoritmos.reels;

public class Reel {

    private final int reelId;
    private final int reelWidth;
    private final int reelDemand;

    private final int reelPriority;

    /**
     * Constructor method
     * 
     * @param id    - identification of the reel
     * @param width - width of the reel
     */
    public Reel(int id, int width, int demand, int priority) {
        this.reelId = id;
        this.reelWidth = width;
        this.reelDemand = demand;
        this.reelPriority = priority;
    }

    // @return reelWidth - Variable containing the information of the width of the
    // reel
    public int getReelId() {
        return this.reelId;
    }

    // @return reelWidth - Variable containing the information of the width of the
    // reel
    public int getReelWidth() {
        return this.reelWidth;
    }

    public int getReelDemand() {
        return this.reelDemand;
    }

    public int getReelPriority() {
        return this.reelPriority;
    }

    @Override
    public String toString() {
        return "Reel [reelId=" + reelId + ", reelWidth=" + reelWidth + ", reelDemand=" + reelDemand + ", reelPriority="
                + reelPriority + "]";
    }
}
