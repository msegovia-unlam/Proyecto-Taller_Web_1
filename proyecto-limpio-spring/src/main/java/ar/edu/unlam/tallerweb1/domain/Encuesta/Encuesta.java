package ar.edu.unlam.tallerweb1.domain.Encuesta;
import ar.edu.unlam.tallerweb1.domain.Publicacion.Publicacion;
import ar.edu.unlam.tallerweb1.domain.Votacion.Voto;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Encuesta{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Boolean activa;

    @OneToOne(mappedBy = "encuesta")
    private Publicacion publicacion;

    @OneToMany(mappedBy = "encuesta", cascade = CascadeType.ALL)
    private List <Voto> voto;

    private String opcion1;

    private String opcion2;

    private String opcion3;

    private String opcion4;





}
