package ar.edu.unlam.tallerweb1.domain.libros;

import java.util.List;

public interface ServicioLibro {
    Integer guardarLibro (Libro libro);
    Libro buscarLibroPorId(Integer id);
    void borrarLibro(Libro libro);
    List<Libro> obtenerLibrosALaVenta();
    Integer modificarLibro(Libro libro);
}
