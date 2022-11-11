package ar.edu.unlam.tallerweb1.domain.Votacion;
import ar.edu.unlam.tallerweb1.domain.Encuesta.Encuesta;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Voto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    private Encuesta encuesta;

    private String opcionElegida;

    @ManyToOne
    private Usuario usuarioVotante;



}
