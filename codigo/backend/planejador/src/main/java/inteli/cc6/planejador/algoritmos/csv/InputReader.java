package inteli.cc6.planejador.algoritmos.csv;

public class InputReader {

    /**
     * This function is used to read data from the frontend and convert it into an
     * array of Input objects.
     *
     * @param jumboSize    the jumbo size value from the frontend
     * @param jumboSizeMin the minimum jumbo size value from the frontend
     * @param knivesQtd    the knives quantity value from the frontend
     * @return array of Input objects
     */
    public static Input[] read(int jumboSize, int jumboSizeMin, int knivesQtd) {

        Input input = new Input(jumboSize, jumboSizeMin, knivesQtd);
        Input[] inputs = { input };

        return inputs;
    }

    public static void main(String[] args) {
        // Example usage: retrieve the data from the frontend and pass it to the read
        // method
        int jumboSize = 100;
        int jumboSizeMin = 80;
        int knivesQtd = 2;

        Input[] inputs = read(jumboSize, jumboSizeMin, knivesQtd);

        for (Input input : inputs) {
            System.out.println(input);
        }
    }
}