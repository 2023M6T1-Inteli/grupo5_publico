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

import inteli.cc6.planejador.API.modelo.MaquinaModelo;
import inteli.cc6.planejador.API.modelo.RespostaModelo;
import inteli.cc6.planejador.API.servico.MaquinaServico;

@RestController
@CrossOrigin(origins = "*")
public class MaquinaControle {

    @Autowired
    private MaquinaServico ms;

    @DeleteMapping("maquinas/remover/{codigo}") 
    public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo) {
        return ms.remover(codigo);
    }
    
    @PutMapping ("maquinas/alterar")
    public ResponseEntity<?> alterar(@RequestBody MaquinaModelo pm) {
        return ms.cadastrarAlterar(pm, "alterar");
    }

    @GetMapping("maquinas/listar")
    public Iterable<MaquinaModelo> listar() {
        return ms.listarMaquina();
    }

    @PostMapping("maquinas/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody MaquinaModelo pm) {
        return ms.cadastrarAlterar(pm, "cadastrar");
    }

}
