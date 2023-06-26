package inteli.cc6.planejador.API.repositorio;

import org.springframework.data.repository.CrudRepository;

import inteli.cc6.planejador.API.modelo.MaquinaModelo;


public interface MaquinaRepositorio extends CrudRepository<MaquinaModelo, Long>{    
}
