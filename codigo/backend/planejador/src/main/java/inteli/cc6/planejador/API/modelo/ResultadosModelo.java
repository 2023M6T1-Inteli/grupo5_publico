package inteli.cc6.planejador.API.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "resultados")
@Getter
@Setter
public class ResultadosModelo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long pedido;
    private int qtdeCortes;
    private String tamanhoCortes;
    private int perdaCortes;
}
