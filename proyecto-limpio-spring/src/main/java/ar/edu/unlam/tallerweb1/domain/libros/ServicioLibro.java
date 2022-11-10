package ar.edu.unlam.tallerweb1.domain.libros;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServicioLibro {
    Integer crearLibro(Libro libro, MultipartFile imagen);
    Libro buscarLibroPorId(Integer id);
    void borrarLibro(Libro libro);
    List<Libro> obtenerLibrosALaVenta();
    List<Libro> devolverTodosLosLibros();
    List<Libro> buscarLibroPorTituloALaVenta(String titulo);
    void actualizarLibro(Libro libroAActualizar);
    void cambiarEstadoDeVentaDelLibro(Integer id);
    List<Libro> obtenerLibrosEnNovedad();
    void cambiarEstadoDeNovedadDelLibro(Integer id);
    boolean comprarLibro(Integer idLibro, Integer idUsuario);
    List<Libro> buscarRelacionadosPorAutor(String autor);
    List<Libro> buscarLibroPorAutor(String autor);
    List<Libro> buscarRelacionadosPorGenero(String genero);
    List<Libro> buscarLibrosPorTitulo(String busqueda);
    List<Libro> buscarLibrosPorTituloYAutor(String busqueda);
    boolean verificarStock(Integer idLibro, Integer cantidadDelLibro);
    List<Libro> obtenerLibrosComprados(Integer idUsuario);

    void calificarLibro(Integer idLibro, Integer calificacion, Integer idUsuario);

    Integer obtenerPromedioGlobal(Integer idLibro);

    Integer obtenerUsuariosQueCalificarionUnLibro(Integer idLibro);
}
