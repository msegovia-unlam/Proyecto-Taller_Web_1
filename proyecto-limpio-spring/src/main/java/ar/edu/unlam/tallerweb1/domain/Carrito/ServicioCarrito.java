package ar.edu.unlam.tallerweb1.domain.Carrito;
import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.util.List;

public interface ServicioCarrito{

    void agregarLibroAlCarrito(Usuario usuario, Libro libro);
    void quitarLibroDelCarrito(Integer id, Integer usuario);
    List<Integer> obtenerListaDeIdDeLibrosDelCarrito(Integer usuarioId);

}
