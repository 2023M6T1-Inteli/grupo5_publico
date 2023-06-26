package inteli.cc6.planejador.algoritmos.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    public static int[][] read(String fileName) {
        ArrayList<Order> orders = new ArrayList<>(); // creating an arraylist of Order type

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName)); // parsing a CSV file into BufferedReader

            String line = br.readLine(); // aux variable to store the line data / skipping the first line (head of the
                                         // csv file)

            while ((line = br.readLine()) != null) // checking if there is still a line to be read
            {
                String[] data = line.split(","); // using comma as separator

                Order order = new Order(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
                        Integer.parseInt(data[2])); // creating an Order
                // object using the line
                // data

                orders.add(order); // adding the created Order to the arraylist
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace(); // throws an exception
        }

        // Storing size and quantity values in an array
        int[][] sizeQuantityArray = new int[orders.size()][3];
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            sizeQuantityArray[i][0] = order.getSize();
            sizeQuantityArray[i][1] = order.getQuantity();
            sizeQuantityArray[i][2] = order.getPriority();
        }

        System.out.println("CSV file read successfully.\n");

        return sizeQuantityArray;
    }

    public static void main(String[] args) {
        // Test
        int[][] sizeQuantityArray = read("src/main/java/inteli/cc6/planejador/algoritmos/csv/dados-klabin2.csv");

        for (int i = 0; i < sizeQuantityArray.length; i++) {
            int size = sizeQuantityArray[i][0];
            int quantity = sizeQuantityArray[i][1];
            System.out.println("Size: " + size + ", Quantity: " + quantity);
        }
    }
}
