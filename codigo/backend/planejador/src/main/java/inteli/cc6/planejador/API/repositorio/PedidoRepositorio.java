package inteli.cc6.planejador.API.repositorio;

import org.springframework.data.repository.CrudRepository;

import inteli.cc6.planejador.API.modelo.PedidoModelo;

public interface PedidoRepositorio extends CrudRepository<PedidoModelo, Long>{    
}
