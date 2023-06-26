package inteli.cc6.planejador.API.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "maquina")
@Getter
@Setter
public class MaquinaModelo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private double tamanho;
    private int quantidadeFacas;
    private int larguraJumboMax;
    private int larguraJumboMin; 
    private int multiploTiradas;
}
