package inteli.cc6.planejador.algoritmos.csv;

import inteli.cc6.planejador.algoritmos.results.SolutionCombination;
import inteli.cc6.planejador.algoritmos.reels.Reel;
import inteli.cc6.planejador.algoritmos.reels.ReelList;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * 
 * This class is responsible for generating a CSV file based on a given
 * SolutionCombination object.
 * The CSV file contains information about the solution, reel list details,
 * solution patterns, and reel details.
 */
public class CSVCreator {

    /**
     * Generates a CSV file based on the provided SolutionCombination object and
     * file path.
     *
     * @param solutionCombination The SolutionCombination object containing the
     *                            solution data.
     * @param filePath            The file path where the CSV file will be
     *                            generated.
     */
    public static void generateCSV(SolutionCombination solutionCombination, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);

            // Write the solution ID and reel list details
            writer.append("Id Solução,").append(String.valueOf(solutionCombination.getSolutionId())).append("\n");
            writer.append("Tamanho Jumbo,")
                    .append(String.valueOf(solutionCombination.getSolutionReelList().getReelListJumboSizeMax()))
                    .append("\n");
            writer.append("Tamanho Mín SOlução,")
                    .append(String.valueOf(solutionCombination.getSolutionReelList().getReelListJumboSizeMin()))
                    .append("\n");
            writer.append("Qtd. Facas,")
                    .append(String.valueOf(solutionCombination.getSolutionReelList().getReelListKnivesQtd()))
                    .append("\n");
            writer.append("\n");

            // Write the solution patterns
            int[][] solutionPatterns = solutionCombination.getSolutionPatterns();
            writer.append("Qtd. Vezes Padrão");
            for (int i = 0; i < solutionPatterns[0].length; i++) {
                writer.append(",").append("Qtd Vezes Rolo ").append(String.valueOf(solutionCombination.getSolutionReelList().getWidthsList().get(i)));
            }
            writer.append(",").append("Comprimento em mm perdidos,");
            writer.append("% Perdido no rolo");
            writer.append("\n");
            int[] solutionQtdPatterns = solutionCombination.getSolutionQtdPatterns();
            for (int i = 0; i < solutionPatterns.length; i++) {
                writer.append(String.valueOf(solutionQtdPatterns[i]));
                for (int j = 0; j < solutionPatterns[i].length; j++) {
                    writer.append(",").append(String.valueOf(solutionPatterns[i][j]));
                }

                int colunaTotal = 0;
                for (int j = 0; j < solutionCombination.getSolutionPatterns().length; j++) {
                    colunaTotal += solutionCombination.getSolutionReelList().getWidthsList().get(j) * solutionCombination.getSolutionPatterns()[j][i];
                }
                
                writer.append(",").append(String.valueOf(solutionCombination.getSolutionReelList().getReelListJumboSizeMax() - colunaTotal));
                
                double wastePercent = ((double)((solutionCombination.getSolutionReelList().getReelListJumboSizeMax() - colunaTotal) * solutionCombination.getSolutionQtdPatterns()[i]) / (double)(solutionCombination.getSolutionReelList().getReelListJumboSizeMax()  * solutionCombination.getSolutionQtdPatterns()[i])*100);

                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
                DecimalFormat df = new DecimalFormat("0.00", symbols);
                String valorFormatado;
                
                valorFormatado = df.format(wastePercent);

                writer.append(",").append(valorFormatado+"%");

                writer.append("\n");
            }

            // Write the reel details
            writer.append("\nId Rolo,Tamanho Rolo,Demanda,Produzido,Sobra,Prioridade\n");
            for (Reel reel : solutionCombination.getSolutionReelList().getReelList()) {
                int reelId = reel.getReelId();
                int reelWidth = reel.getReelWidth();
                int reelDemand = reel.getReelDemand();
                int reelProduced = solutionCombination.getQtdReelProduced(reelId);
                int reelWaste = reelProduced - reelDemand;

                writer.append(String.valueOf(reelId)).append(",")
                        .append(String.valueOf(reelWidth)).append(",")
                        .append(String.valueOf(reelDemand)).append(",")
                        .append(String.valueOf(reelProduced)).append(",")
                        .append(String.valueOf(reelWaste)).append(",")
                        .append(String.valueOf(reel.getReelPriority())).append("\n");
            }

            writer.flush();
            writer.close();
            System.out.println("CSV file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method of the CSVCreator class.
     * It demonstrates the usage of the CSVCreator class by generating a CSV file
     * based on sample data.
     * The sample data includes reel objects, reel list parameters, solution
     * patterns, and a solution combination.
     * The generated CSV file contains information about the solution, reel list
     * details, solution patterns, and reel details.
     * 
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        // Crie um objeto ReelList com os dados dos rolos e outros detalhes
        Reel reel1 = new Reel(0, 200, 5, 1);
        Reel reel2 = new Reel(1, 200, 6, 1);
        Reel reel3 = new Reel(2, 400, 4, 2);

        int reelListJumboSizeMax = 6000;
        int reelListJumboSizeMin = 5800;
        int reelListKnivesQtd = 2;

        ReelList solutionReelList = new ReelList(0, reelListJumboSizeMax, reelListJumboSizeMin, reelListKnivesQtd);

        solutionReelList.addReel(reel1);
        solutionReelList.addReel(reel2);
        solutionReelList.addReel(reel3);

        // Crie os arrays de dados para a solução
        double[] solutionQtdPatterns = { 2, 3, 1 };
        double[][] solutionPatterns = {
                { 3, 4, 1 },
                { 0, 3, 4 },
                { 3, 2, 1 }
        };

        // Crie um objeto SolutionCombination com os dados da solução
        SolutionCombination solutionCombination = new SolutionCombination(solutionReelList, solutionQtdPatterns,
                solutionPatterns);

        // Defina o caminho para salvar o arquivo CSV
        String filePath = "codigo/backend/planejador/src/main/java/inteli/cc6/planejador/algoritmos/reader/solution.csv";

        // Gere e salve o arquivo CSV
        CSVCreator.generateCSV(solutionCombination, filePath);
    }
}
