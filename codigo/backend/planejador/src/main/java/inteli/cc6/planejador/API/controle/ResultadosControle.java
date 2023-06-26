package inteli.cc6.planejador.API.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import inteli.cc6.planejador.API.modelo.RespostaModelo;
import inteli.cc6.planejador.API.modelo.ResultadosModelo;
import inteli.cc6.planejador.API.servico.ResultadosServico;

@RestController
@CrossOrigin(origins = "*")
public class ResultadosControle {

    @Autowired
    private ResultadosServico rs;

    @DeleteMapping("resultados/remover/{codigo}") 
    public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo) {
        return rs.remover(codigo);
    }
    
    @PutMapping ("resultados/alterar")
    public ResponseEntity<?> alterar(@RequestBody ResultadosModelo pm) {
        return rs.cadastrarAlterar(pm, "alterar");
    }

    @GetMapping("resultados/listar")
    public Iterable<ResultadosModelo> listar() {
        return rs.listar();
    }

    @GetMapping("resultados/listar/{codigo}")
    public Iterable<ResultadosModelo> listarPorPedido(@PathVariable Long codigo) {
        return rs.listarPorPedido(codigo);
    }


    @PostMapping("resultados/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ResultadosModelo pm) {
        return rs.cadastrarAlterar(pm, "cadastrar");
    }

}
