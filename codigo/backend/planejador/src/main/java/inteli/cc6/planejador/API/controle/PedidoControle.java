package inteli.cc6.planejador.API.controle;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import inteli.cc6.planejador.API.modelo.PedidoModelo;
import inteli.cc6.planejador.API.modelo.RespostaModelo;
import inteli.cc6.planejador.API.servico.PedidoServico;

@RestController
@CrossOrigin(origins = "*")
public class PedidoControle {

    @Autowired
    private PedidoServico ps;

    @DeleteMapping("pedidos/remover/{codigo}") 
    public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo) {
        return ps.remover(codigo);
    }
    
    @PutMapping ("pedidos/alterar")
    public ResponseEntity<?> alterar(@RequestBody PedidoModelo pm) {
        return ps.cadastrarAlterar(pm, "alterar");
    }

    @GetMapping("pedidos/listar")
    public Iterable<PedidoModelo> listar() {
        return ps.listar();
    }

    @PostMapping("pedidos/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody PedidoModelo pm) {
        return ps.cadastrarAlterar(pm, "cadastrar");
    }

    @GetMapping("/")
    public String rota() {
        return "Hello World!";
    }

    @PostMapping("pedidos/run/{codigo}")
    public ResponseEntity<?> run(@PathVariable long codigo) {
        return ps.run(codigo);
    }

    @PostMapping("pedidos/upload")
    public ResponseEntity<?> cadastrar(@RequestParam("file") MultipartFile file) {
        return ps.salvarCSV(file);
    }

    @GetMapping("pedidos/download/{codigo}")
    public ResponseEntity<?> baixarCSV(@PathVariable long codigo) throws IOException{
        return ps.baixarCSV(codigo);
    }
    

}
