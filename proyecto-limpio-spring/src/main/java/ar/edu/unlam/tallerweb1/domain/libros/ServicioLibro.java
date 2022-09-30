package ar.edu.unlam.tallerweb1.domain.libros;

import java.util.List;

public interface ServicioLibro {
    Long guardarLibro (Libro libro);

    Libro buscarLibroPorId(Long id);

    void borrarLibro(Libro libro);

    List<Libro> obtenerLibrosALaVenta();
}
