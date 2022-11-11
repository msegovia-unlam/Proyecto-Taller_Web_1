package ar.edu.unlam.tallerweb1.domain.Publicacion;
import ar.edu.unlam.tallerweb1.domain.Encuesta.Encuesta;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
public class Publicacion{

    public Publicacion(String publicacion, LocalDateTime fecha, Usuario usuario, Encuesta encuesta){

        this.publicacion = publicacion;
        this.fecha = fecha;
        this.usuario = usuario;
        this.encuesta=encuesta;
    }
    public Publicacion(String publicacion, LocalDateTime fecha, Usuario usuario){

        this.publicacion = publicacion;
        this.fecha = fecha;
        this.usuario = usuario;
    }


    public Publicacion(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String publicacion;

    @OneToOne (cascade = CascadeType.ALL)
    private Encuesta encuesta;

    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
}
