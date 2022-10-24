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
    List<Libro> buscarLibroPorTituloALaVenta(String titulo);
    void cambiarEstadoDeVentaDelLibro(Integer id);
    List<Libro> obtenerListaDeLibrosEnNovedad();
    void cambiarEstadoDeNovedadDelLibro(Integer id);
    boolean reducirStock(Integer idLibro);
    List<Libro> buscarRelacionadosPorAutor(String autor);
    List<Libro> devolverLibroPorAutor(String autor);
    List<Libro> buscarRelacionadosPorGenero(String genero);
    List<Libro> buscarLibrosPorTitulo(String titulo);
    List<Libro> buscarLibrosPorAutor(String busqueda);
    void asignarLibroComprado(Libro libro, Usuario usuario);
    List<Libro> obtenerLibrosComprados(Usuario usuario);
}
