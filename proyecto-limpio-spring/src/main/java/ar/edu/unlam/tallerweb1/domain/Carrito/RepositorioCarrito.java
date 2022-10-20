package ar.edu.unlam.tallerweb1.domain.Carrito;
import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.util.List;

public interface RepositorioCarrito{

    void agregarLibroAlCarrito(Usuario usuario, Libro libro);
    List <Integer> obtenerListaDeIdDeLibrosDelCarrito(Usuario usuario);
    void quitarLibroDelCarrito(Usuario usuario, Libro libro);

}
