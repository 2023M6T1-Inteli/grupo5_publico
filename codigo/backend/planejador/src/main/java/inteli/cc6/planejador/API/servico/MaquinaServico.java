package inteli.cc6.planejador.API.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import inteli.cc6.planejador.API.modelo.MaquinaModelo;
import inteli.cc6.planejador.API.modelo.RespostaModelo;
import inteli.cc6.planejador.API.repositorio.MaquinaRepositorio;

@Service
public class MaquinaServico {
    
    @Autowired
    private MaquinaRepositorio mr;

    @Autowired
    private RespostaModelo rm;

    // Método para listar todos os produtos
    public Iterable<MaquinaModelo> listarMaquina() {
        return mr.findAll();
    }


    // Método para cadastrar ou alterar produtos
    public ResponseEntity<?> cadastrarAlterar(MaquinaModelo mm, String acao) {

        if (mm.getNome().equals("")) {
            rm.setMensagem("O nome do produto é obrigatório!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if(mm.getTamanho() == 0.0) {
            rm.setMensagem("O tamanho do produto é obrigatório!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else {
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<MaquinaModelo>(mr.save(mm), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<MaquinaModelo>(mr.save(mm), HttpStatus.OK);
            }
        }
    }

    // Método para remover produtos
    public ResponseEntity<RespostaModelo> remover(Long codigo) {
        mr.deleteById(codigo);

        rm.setMensagem("O produto foi removido com sucesso!");

        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);

    }

}
