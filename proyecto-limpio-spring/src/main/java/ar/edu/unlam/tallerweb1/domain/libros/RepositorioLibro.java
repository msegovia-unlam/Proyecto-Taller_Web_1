package ar.edu.unlam.tallerweb1.domain.libros;

import java.util.List;

public interface RepositorioLibro {
    Integer guardarLibro(Libro libro);
    List<Libro> obtenerListaDeLibros();
    Libro buscarLibroPorId(Integer id);
    void borrarlibro(Libro libro);
    Integer modificarLibro(Libro libro);
}
