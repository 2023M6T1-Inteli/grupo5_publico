package inteli.cc6.planejador.API.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "pedido")
@Getter
@Setter
public class PedidoModelo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long maquina;
    private String nome;
    private String data;
    private int quantidadeBobinas;
    private int quantidadeRolosJumbo; 
    private double tamanhoMedioPonta;
    private double porcentagemPerda;
    private String csvPath;
    private String resultPath;

}
