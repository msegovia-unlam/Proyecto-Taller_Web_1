package ar.edu.unlam.tallerweb1.domain.libros;

import java.util.List;

public interface RepositorioLibro {
    Long guardarLibro(Libro libro);
    List<Libro> obtenerListaDeLibros();
    Libro buscarLibroPorId(Long id);
    void borrarlibro(Libro libro);
}
