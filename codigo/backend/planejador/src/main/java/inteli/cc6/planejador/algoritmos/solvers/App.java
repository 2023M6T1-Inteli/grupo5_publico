package inteli.cc6.planejador.algoritmos.solvers;

import inteli.cc6.planejador.algoritmos.csv.CSVCreator;
import inteli.cc6.planejador.algoritmos.csv.CSVReader;
import inteli.cc6.planejador.algoritmos.reels.Reel;
import inteli.cc6.planejador.algoritmos.reels.ReelList;
import inteli.cc6.planejador.algoritmos.results.SolutionCombination;
import inteli.cc6.planejador.algoritmos.simplex.ColumnGenarationSimplex;
import inteli.cc6.planejador.algoritmos.simplex.ColumnGenarationSimplexWithBranchAndBoundColumnGenerator;

/**
 * The entry point of the application
 */
public class App {

    /**
     * The main method that starts the application.
     *
     * @param args The command line arguments.
     */

    public static String[] run(int id, String csvPath, int jumboSizeMax,  int jumboSizeMin, int knivesQtd, int priority) {

        // Pega o csvPath e coloca "-solution" no nome do arquivo
        String[] csvPathSplit = csvPath.split("\\.");
        String filePath = csvPathSplit[0] + "-solution.csv";

        System.out.println(filePath);

        // ReelList
        ReelList reelList = new ReelList(id, jumboSizeMax, jumboSizeMin, knivesQtd);

        // Reading CSV
        int[][] file = CSVReader.read(
                csvPath);

        for (int i = 0; i < file.length; i++) {
            reelList.addReel(new Reel(i, file[i][0], file[i][1], file[i][2]));
        }
        double totalTemp = System.nanoTime();
        ColumnGenarationSimplex generateSolution = new ColumnGenarationSimplex();
        // SolutionCombination results =
        // generateSolution.columnGenerationSimplex(reelList);
        SolutionCombination results = ColumnGenarationSimplexWithBranchAndBoundColumnGenerator.solve(reelList);

        long passoEndTime = System.nanoTime();
        totalTemp = (passoEndTime - totalTemp) / 1e9;

        System.out.println("Tempo em ms: " + totalTemp * 1000);

        int[] qtdPatterns = results.getSolutionQtdPatterns();
        int[][] patterns = results.getSolutionPatterns();
        System.out.println("\n");

        System.out.print("Vetor de quantidades por padrão: ");

        for (int i = 0; i < qtdPatterns.length; i++) {
            System.out.print(qtdPatterns[i] + "|");
        }

        System.out.println("\n\n" + "Vetor de padrões: ");

        for (int i = 0; i < patterns.length; i++) {
            for (int j = 0; j < patterns[i].length; j++) {
                if (patterns[i][j] > 9) {
                    System.out.print(patterns[i][j] + "|");
                } else {
                    System.out.print(patterns[i][j] + " |");
                }
            }
            System.out.println();
        }

        int[] vetorBobinas = new int[results.getSolutionReelList().getWidthsList().size()];

        // Copiar os elementos do ArrayList para o array
        for (int i = 0; i < results.getSolutionReelList().getWidthsList().size(); i++) {
            vetorBobinas[i] = results.getSolutionReelList().getWidthsList().get(i);
        }

        int[] vetorQuantidadesGeradas = new int[qtdPatterns.length];

        for (int i = 0; i < patterns.length; i++) {
            for (int j = 0; j < patterns[i].length; j++) {
                vetorQuantidadesGeradas[i] += patterns[i][j] * qtdPatterns[j];
            }
        }

        System.out.print("\n\n" + "Vetor de quantidades geradas: ");
        for (int quantidadeGerada : vetorQuantidadesGeradas) {
            System.out.print(quantidadeGerada + " ");
        }
        System.out.println();

        int valorTotal = jumboSizeMax;

        int[] vetorSubtracao = new int[qtdPatterns.length];

        for (int i = 0; i < patterns[0].length; i++) {
            int colunaTotal = 0;
            for (int j = 0; j < patterns.length; j++) {
                colunaTotal += vetorBobinas[j] * patterns[j][i];
            }
            vetorSubtracao[i] = valorTotal - colunaTotal;
        }

        System.out.print("\n\n" + "Vetor de subtração: ");
        for (int valorSubtracao : vetorSubtracao) {
            System.out.print(valorSubtracao + " ");
        }
        System.out.println();

        double[] porcentagemPerda = new double[qtdPatterns.length];

        for (int i = 0; i < vetorSubtracao.length; i++) {

            porcentagemPerda[i] = ((double) (vetorSubtracao[i] * vetorQuantidadesGeradas[i])
                    / (double) (valorTotal * vetorQuantidadesGeradas[i]));

        }

        System.out.print("\n\n" + "% de Perda por padrão: ");
        for (double valor : porcentagemPerda) {
            System.out.printf("%.2f%% ", valor * 100);
        }
        System.out.println();
      
        System.out.print("\n\n" + "% média de perda: ");

        double medianLostPercentage = 0.0;
        for (double valor : porcentagemPerda) {
            medianLostPercentage += valor;
        }
        double perdaMedia = Math.ceil((medianLostPercentage / vetorBobinas.length) * jumboSizeMax);
        double porcentagemPerdaMedia = (medianLostPercentage * 100) / vetorBobinas.length;
        System.out.printf("%.2f%% ", porcentagemPerdaMedia);
        System.out.print("\t" + "Perda real média em mm: "
                + perdaMedia + "\n");
        System.out.println();

        // Gere e salve o arquivo CSV
        CSVCreator.generateCSV(results, filePath);

        String[] solutions = new String[qtdPatterns.length + 1];

        int qtdeRolos = 0;

        for (int i = 0; i < patterns.length; i++) {
            String patternString = "";
            patternString = qtdPatterns[i] + "x [";
            qtdeRolos = qtdeRolos + qtdPatterns[i];
            for (int j = 0; j < patterns[i].length; j++) {
                int valorBobina = reelList.getWidthsList().get(j);
                int quantidade = patterns[j][i];
                while(quantidade > 0) {
                    patternString = patternString + valorBobina + " ";
                    quantidade--;
                }
            }
            patternString = patternString + "] " + "Perda = " + vetorSubtracao[i];
            solutions[i] = patternString;
            System.out.println();
        }

        solutions[qtdPatterns.length] = (perdaMedia + " " + porcentagemPerdaMedia + " " + qtdPatterns.length + " " + qtdeRolos + " " + filePath);

        return solutions;

    }

    public static void main(String[] args) {
        System.out.println("Inteli - Ciência da Computação - Modulo 6");
        System.out.println("Módulo Planejador");

        int jumboSizeMax = 6030;
        int knivesQtd = 5;
        int jumboSizeMin = 5800;
        String csvPath = "codigo\\backend\\planejador\\src\\main\\java\\inteli\\cc6\\planejador\\algoritmos\\csv\\dados-klabin2.csv";
        int id = 0;

        run(id, csvPath, jumboSizeMax, jumboSizeMin, knivesQtd, 1);
    }

}
