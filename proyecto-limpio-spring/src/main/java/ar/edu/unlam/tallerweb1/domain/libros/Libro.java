package ar.edu.unlam.tallerweb1.domain.libros;

import ar.edu.unlam.tallerweb1.domain.Carrito.Carrito;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "Libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "numeroDePaginas")
    private Integer numeroDePaginas;

    @Column(name = "autor")
    private String autor;

    @Column(name = "genero")
    private String genero;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagen")
    private ImagenLibro imagen;

    @Column(name = "editorial")
    private String editorial;

    @Column(name = "tipoDeObra")
    private String tipoDeObra;

    @Column(name = "agnoDeImpresion")
    private String agnoDeImpresion;

    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "precioDeVenta")
    private Integer precioDeVenta;

    @Column(name = "aLaVenta")
    private Boolean aLaVenta;

    @Column(name = "sinopsis", length = 9999)
    private String sinopsis;

    @Column(name = "novedad")
    private Boolean novedad;

    @OneToMany(mappedBy = "libro")
    private List<Carrito> carrito;

    @Column(name = "cantidadEnStock")
    private Integer cantidadEnStock;

}
