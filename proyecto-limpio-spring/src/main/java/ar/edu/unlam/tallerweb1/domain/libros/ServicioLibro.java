package ar.edu.unlam.tallerweb1.domain.libros;

import java.util.List;

public interface ServicioLibro {
    Long guardarLibro (Libro libro);

    List<Libro> obtenerLibrosALaVenta();
}
