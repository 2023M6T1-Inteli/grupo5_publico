package inteli.cc6.planejador.API.servico;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import inteli.cc6.planejador.API.modelo.MaquinaModelo;
import inteli.cc6.planejador.API.modelo.PedidoModelo;
import inteli.cc6.planejador.API.modelo.RespostaModelo;
import inteli.cc6.planejador.API.modelo.ResultadosModelo;
import inteli.cc6.planejador.API.repositorio.MaquinaRepositorio;
import inteli.cc6.planejador.API.repositorio.PedidoRepositorio;
import inteli.cc6.planejador.algoritmos.solvers.App;


@Service
public class PedidoServico {

    @Autowired
    private PedidoRepositorio pr;

    @Autowired
    private MaquinaRepositorio mr;

    @Autowired
    private RespostaModelo rm;

    @Autowired
    private ResultadosServico rs;

    public Iterable<PedidoModelo> listar() {
        return pr.findAll();
    }

    // Método para cadastrar ou alterar produtos
    public ResponseEntity<?> cadastrarAlterar(PedidoModelo pm, String acao) {

        if (pm.getNome().equals("")) {
            rm.setMensagem("O nome do produto é obrigatório!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (pm.getData().equals(null)) {
            rm.setMensagem("O nome da marca é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else {
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<PedidoModelo>(pr.save(pm), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<PedidoModelo>(pr.save(pm), HttpStatus.OK);
            }
        }
    }

    // Método para remover produtos
    public ResponseEntity<RespostaModelo> remover(Long codigo) {

        PedidoModelo pm = pr.findById(codigo).orElseThrow(() -> new RuntimeException("Pedido não existe!"));

        long id = pm.getId();

        rs.removerPorPedido(id);

        // Delete the CSV files according to their paths. 
        String csvPath = pm.getCsvPath();
        String resultPath = pm.getResultPath();

        Path path = Paths.get(csvPath);
        Path path2 = Paths.get(resultPath);

        try {
            Files.delete(path);
            Files.delete(path2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        pr.deleteById(codigo);

        rm.setMensagem("O pedido foi removido com sucesso!");

        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);

    }

    public ResponseEntity<RespostaModelo> run(long codigo) {

        System.out.println("Entrou no run");

        PedidoModelo pm = pr.findById(codigo).orElseThrow(() -> new RuntimeException("Pedido não existe!"));

        rs.removerPorPedido(codigo);

        long maquinaID = pm.getMaquina();

        String csvPath = pm.getCsvPath();

        MaquinaModelo mm = mr.findById(maquinaID).orElseThrow(() -> new RuntimeException("Maquina não existe!"));

        int qtdeFacas = mm.getQuantidadeFacas();

        int jumboSizeMax = mm.getLarguraJumboMax();

        int jumboSizeMin = (int) (jumboSizeMax * 0.9);

        String[] results = App.run((int)codigo, csvPath , jumboSizeMax, jumboSizeMin, qtdeFacas, 1);

        String update = results[results.length - 1];

        // Removing subarray from array
        results = Arrays.copyOfRange(results, 0, results.length - 1);

        // Taking away just the very last part of the string by splitting it
        String resultPath = update.split(" ")[update.split(" ").length - 1];

        // Removing subarray from array
        update = update.substring(0, update.length() - resultPath.length() - 1);

        // Transforming string into array

        double[] updateArray = Arrays.stream(update.split(" ")).mapToDouble(Double::parseDouble).toArray();

        atualizarComResultados(updateArray[0], updateArray[1], (int)updateArray[2], (int)updateArray[3], resultPath, codigo);

        System.out.println("cadastrar ");
        rs.cadastrarResultados(results, codigo);

        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
    }

    public void atualizarComResultados(double perdaMedia, double porcentagemPerdaMedia, int qtdPatterns, int qtdeRolos, String resultPath, long codigo) {

        String data = java.sql.Date.valueOf(java.time.LocalDate.now()).toString();
        String[] date = data.split("-");
        data = date[2] + "/" + date[1] + "/" + date[0];
        
        PedidoModelo pm = pr.findById(codigo).orElseThrow(() -> new RuntimeException("Pedido não existe!"));

        double porcentagemPerda = Math.round(porcentagemPerdaMedia);

        pm.setTamanhoMedioPonta(perdaMedia);
        pm.setPorcentagemPerda(porcentagemPerda);
        pm.setData(data);
        pm.setQuantidadeBobinas(qtdPatterns);
        pm.setQuantidadeRolosJumbo(qtdeRolos);
        pm.setResultPath(resultPath);

        cadastrarAlterar(pm, "alterar");
    }

    public ResponseEntity<RespostaModelo> salvarCSV(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            // Handle the case where no file was selected
            return ResponseEntity.badRequest().build();
        }

        
        try {
            // Get the file name and extension
            String fileName = file.getOriginalFilename();

            // Validate if the file is a CSV file if needed
            if (!fileName.endsWith(".csv")) {
                // Handle the case where the file is not a CSV
                return ResponseEntity.badRequest().build();
            }

            // Changes the file name to the current date
            String dateTime = java.time.LocalDateTime.now().toString();
            dateTime = dateTime.replace(":", "");
            dateTime = dateTime.replace("-", "");
            dateTime = dateTime.split("\\.")[0];
            System.out.println(dateTime);
            fileName = dateTime + ".csv";

            System.out.println("Arquivo recebido: " + fileName);

            // Save the file to the desired location
            // Replace "path/to/save" with the actual path where you want to save the file
            Path filePath = Paths.get("codigo\\backend\\planejador\\src\\main\\java\\inteli\\cc6\\planejador\\algoritmos\\csv", fileName);
            Files.write(filePath, file.getBytes());

            // Return the filepath in the system
            rm.setMensagem(filePath.toString());

            System.out.println("Arquivo salvo com sucesso no local: " + filePath.toString());

            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);

        } catch (IOException e) {
            // Log the error message
            System.out.println("Could not upload the file: " + file.getOriginalFilename() + "!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> baixarCSV(long codigo) throws IOException{

        PedidoModelo pm = pr.findById(codigo).orElseThrow(() -> new RuntimeException("Pedido não existe!"));

        String filePath = pm.getResultPath();
        // Read the file contents into a byte array
        byte[] fileContent = Files.readAllBytes(Paths.get(filePath));

        // Set the appropriate headers for the CSV file download
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDispositionFormData("attachment", "data.csv");

        // Return the CSV file contents as a ResponseEntity
        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.parseMediaType("text/csv"))
            .body(new ByteArrayResource(fileContent));
        }
    }