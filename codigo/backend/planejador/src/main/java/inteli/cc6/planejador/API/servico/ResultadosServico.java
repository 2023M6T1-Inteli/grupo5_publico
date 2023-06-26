package inteli.cc6.planejador.API.servico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import inteli.cc6.planejador.API.modelo.RespostaModelo;
import inteli.cc6.planejador.API.modelo.ResultadosModelo;
import inteli.cc6.planejador.API.repositorio.ResultadosRepositorio;

@Service
public class ResultadosServico {
    
    @Autowired
    private ResultadosRepositorio rr;

    @Autowired
    private RespostaModelo rm;

    public Iterable<ResultadosModelo> listar() {
        return rr.findAll();
    }

    public Iterable<ResultadosModelo> listarPorPedido(Long codigo) {
        List<ResultadosModelo> resultados = new ArrayList<>();

        for (ResultadosModelo rm : rr.findAll()) {
            if (Long.valueOf(rm.getPedido()).equals(codigo)) {
                resultados.add(rm);
            }
        }

        return resultados;
    }



    // Método para cadastrar ou alterar produtos
    public ResponseEntity<?> cadastrarAlterar(ResultadosModelo rm, String acao) {
        if (acao.equals("cadastrar")) {
            return new ResponseEntity<ResultadosModelo>(rr.save(rm), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<ResultadosModelo>(rr.save(rm), HttpStatus.OK);
        }
    }

    // Método para remover produtos
    public ResponseEntity<RespostaModelo> remover(Long codigo) {
        rr.deleteById(codigo);

        rm.setMensagem("O pedido foi removido com sucesso!");

        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);

    }

    public ResponseEntity<RespostaModelo> removerPorPedido(Long codigo) {
        for (ResultadosModelo rm : rr.findAll()) {
            if (Long.valueOf(rm.getPedido()).equals(codigo)) {
                rr.deleteById(rm.getId());
            }
        }

        rm.setMensagem("Os resultados foram removidos com sucesso!");

        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
    }

    public void cadastrarResultados (String[] results, long codigo) {

        for(String result : results) {
            int qtdeCortes = Integer.parseInt(result.split("x")[0]);
            String tamanhoCortes = result.substring(result.indexOf("[") + 1, result.indexOf("]"));
            int perdaCortes = Integer.parseInt(result.split("Perda = ")[1]);

            // Create a new ResultadosModelo object and set its properties
            ResultadosModelo rm = new ResultadosModelo();
            rm.setPedido(codigo);
            rm.setQtdeCortes(qtdeCortes);
            rm.setTamanhoCortes(tamanhoCortes);
            rm.setPerdaCortes(perdaCortes);

            // Call the cadastrarAlterar method of the ResultadosServico service to save the object to the database
            cadastrarAlterar(rm, "cadastrar");
        }
    }

}
