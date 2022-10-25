package ar.edu.unlam.tallerweb1.domain.Follows;
import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
public class Follows{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="usuario_seguidor")
    private Usuario usuarioSeguidor;

    @ManyToOne
    @JoinColumn(name="usuario_seguido")
    private Usuario usuarioSeguido;


}
