package inteli.cc6.planejador.algoritmos.csv;

/**
 * Represents input data containing information about jumbo size, minimum jumbo
 * size, and quantity of knives.
 */
public class Input {

    private int jumboSize;
    private int jumboSizeMin;
    private int knivesQtd;

    /**
     * Constructs an Input object with the specified jumbo size, minimum jumbo size,
     * and quantity of knives.
     *
     * @param size      The jumbo size.
     * @param qty       The minimum jumbo size.
     * @param knivesQtd The quantity of knives.
     */
    public Input(int size, int qty, int knivesQtd) {
        this.jumboSize = size;
        this.jumboSizeMin = qty;
        this.knivesQtd = knivesQtd;
    }

    /**
     * Gets the jumbo size.
     *
     * @return The jumbo size.
     */
    public int getJumboSize() {
        return jumboSize;
    }

    /**
     * Gets the minimum jumbo size.
     *
     * @return The minimum jumbo size.
     */
    public int getJumboSizeMin() {
        return jumboSizeMin;
    }

    /**
     * Gets the quantity of knives.
     *
     * @return The quantity of knives.
     */
    public int getKnivesQtd() {
        return knivesQtd;
    }

    /**
     * Returns a string representation of the Input object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "InputData [jumboSize=" + jumboSize + ", jumboSizeMin=" + jumboSizeMin + ", KnivesQtd=" + " " + knivesQtd
                + "]";
    }
}
