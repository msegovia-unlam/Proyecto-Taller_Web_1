package ar.edu.unlam.tallerweb1.domain.Carrito;
import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Carrito")
public class Carrito{

    public Carrito(){

    }

    public Carrito(Usuario usuario, Libro libro, Integer cantidad){

        this.usuario = usuario;
        this.libro = libro;
        this.cantidad = cantidad;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id_libro")
    private Libro libro;

    @Column(name = "Cantidad")
    private Integer cantidad;


}
