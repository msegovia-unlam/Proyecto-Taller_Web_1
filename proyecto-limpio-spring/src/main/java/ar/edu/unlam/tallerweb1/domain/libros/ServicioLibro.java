package ar.edu.unlam.tallerweb1.domain.libros;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServicioLibro {
    Integer crearLibro(Libro libro, MultipartFile imagen);
    Libro buscarLibroPorId(Integer id);
    void borrarLibro(Libro libro);
    List<Libro> obtenerLibrosALaVenta();
    List<Libro> devolverTodosLosLibros();
    List<Libro> buscarLibroPorTitulo(String titulo);
    void actualizarLibro(Libro libroAActualizar);
    void cambiarEstadoDeVentaDelLibro(Integer id);
    List<Libro> obtenerLibrosEnNovedad();
    void cambiarEstadoDeNovedadDelLibro(Integer id);
    boolean comprarLibro(Integer idLibro);

    List<Libro> buscarRelacionadosPorAutor(String autor);

    List<Libro> buscarLibroPorAutor(String autor);
    List<Libro> buscarRelacionadosPorGenero(String genero);
}
