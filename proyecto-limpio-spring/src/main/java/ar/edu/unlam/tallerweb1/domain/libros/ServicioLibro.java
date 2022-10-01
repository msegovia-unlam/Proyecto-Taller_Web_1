package ar.edu.unlam.tallerweb1.domain.libros;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServicioLibro {
    Integer crearLibro(Libro libro, MultipartFile imagen);
    Libro buscarLibroPorId(Integer id);
    void borrarLibro(Libro libro);
    List<Libro> obtenerLibrosALaVenta();
    Integer modificarLibro(Libro libro);
}
