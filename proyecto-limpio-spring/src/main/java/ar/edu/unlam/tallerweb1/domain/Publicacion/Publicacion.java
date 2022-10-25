package ar.edu.unlam.tallerweb1.domain.Publicacion;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Entity
public class Publicacion{

    public Publicacion(String publicacion, LocalDateTime fecha, Integer meGusta, Usuario usuario){

        this.publicacion = publicacion;
        this.fecha = fecha;
        this.meGusta = meGusta;
        this.usuario = usuario;
    }

    public Publicacion(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String publicacion;

    private LocalDateTime fecha;

    private Integer meGusta;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
}
