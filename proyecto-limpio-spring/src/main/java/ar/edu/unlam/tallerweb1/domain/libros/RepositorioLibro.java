package ar.edu.unlam.tallerweb1.domain.libros;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.util.List;

public interface RepositorioLibro {
    Libro guardarLibro(Libro libro);
    List<Libro> obtenerListaDeLibrosALaVenta();
    Libro buscarLibroPorId(Integer id);
    void borrarlibro(Libro libro);
    void actualizarLibro(Libro libro);
    List<Libro> devolverTodosLosLibros();
    List<Libro> buscarLibroPorTitulo(String titulo);
    void cambiarEstadoDeVentaDelLibro(Integer id);

    List<Libro> obtenerListaDeLibrosEnNovedad();

    void cambiarEstadoDeNovedadDelLibro(Integer id);

//    void guardarLibroEnCarrito(Carrito carrito, Libro libro, Usuario usuario);
}
