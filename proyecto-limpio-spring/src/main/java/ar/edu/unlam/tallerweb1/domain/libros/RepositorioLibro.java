package ar.edu.unlam.tallerweb1.domain.libros;

import java.util.List;

public interface RepositorioLibro {
    Libro guardarLibro(Libro libro);
    List<Libro> obtenerListaDeLibros();
    Libro buscarLibroPorId(Integer id);
    void borrarlibro(Libro libro);
    void actualizarLibro(Libro libro);
    List<Libro> devolverTodosLosLibros();
    List<Libro> buscarLibroPorTitulo(String titulo);
    void cambiarEstadoDeVentaDelLibro(Integer id);
    List<Libro> obtenerListaDeLibrosEnNovedad();
    void cambiarEstadoDeNovedadDellibro(Integer id);
    boolean reducirStock(Integer idLibro);
    List<Libro> buscarRelacionadosPorAutor(String autor);
    List<Libro> devolverLibroPorAutor(String autor);
    List<Libro> buscarRelacionadosPorGenero(String genero);
}
