package ar.edu.unlam.tallerweb1.domain.libros;

import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@Transactional
public class ServicioLibroImpl implements ServicioLibro {

    private RepositorioLibro repositorioLibro;
    private ServletContext servletContext;
    private RepositorioUsuario repositorioUsuario;


    @Autowired
    public ServicioLibroImpl(RepositorioLibro repositorioLibro, ServletContext servletContext, RepositorioUsuario repositorioUsuario) {
        this.repositorioLibro = repositorioLibro;
        this.servletContext = servletContext;
        this.repositorioUsuario=repositorioUsuario;
    }

    @Override
    public Integer crearLibro(Libro libro, MultipartFile imagen) {
        // Pregunto si la imagen que se subio esta vacia o es nula
        if (!imagen.isEmpty() && imagen != null) {
            // Guardo el libro y su imagen en la base de datos
            ImagenLibro imagenLibro = new ImagenLibro();
            libro.setImagen(imagenLibro);
            libro = repositorioLibro.guardarLibro(libro);
            // Guardo la imagen en el servidor
            try {
                // Creo la carpeta images si no existe
                Path carpetaImages = Path.of(servletContext.getRealPath("/") + "images");
                if (!Files.exists(carpetaImages))
                    Files.createDirectory(carpetaImages);
                String nombreDeLaImagen = carpetaImages + "\\" + libro.getImagen().getId() + ".jpg";
                imagen.transferTo(new File(nombreDeLaImagen));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return libro.getId();
    }

    @Override
    public Libro buscarLibroPorId(Integer id) {
        return repositorioLibro.buscarLibroPorId(id);
    }

    @Override
    public void borrarLibro(Libro libro) {
        repositorioLibro.borrarlibro(libro);
    }

    @Override
    public List<Libro> obtenerLibrosALaVenta() {
        List<Libro> listaDeLibrosALaVenta = repositorioLibro.obtenerListaDeLibrosALaVenta();
        return listaDeLibrosALaVenta;
    }

    @Override
    public void actualizarLibro(Libro libro) {
        repositorioLibro.actualizarLibro(libro);
    }

    @Override
    public void cambiarEstadoDeVentaDelLibro(Integer id) {
        repositorioLibro.cambiarEstadoDeVentaDelLibro(id);
    }

    @Override
    public List<Libro> obtenerLibrosEnNovedad() {
       return repositorioLibro.obtenerListaDeLibrosEnNovedad();
    }

    @Override
    public void cambiarEstadoDeNovedadDelLibro(Integer id) {
        repositorioLibro.cambiarEstadoDeNovedadDelLibro(id);
    }

    @Override
    public boolean comprarLibro(Integer idLibro) {
        Libro libro = repositorioLibro.buscarLibroPorId(idLibro);
        if(libro == null)
            return false;
        int librosEnStock = libro.getCantidadEnStock();
        if(librosEnStock > 0)
            return this.repositorioLibro.reducirStock(idLibro);
        else
            return false;
    }

    @Override
    public List<Libro> buscarRelacionadosPorAutor(String autor) {
        List<Libro> listaRelacionados = repositorioLibro.buscarRelacionadosPorAutor(autor);
        return listaRelacionados;
    }

    @Override
    public List<Libro> buscarLibroPorAutor(String autor) {
        return repositorioLibro.devolverLibroPorAutor(autor);
    }

    @Override
    public List<Libro> buscarRelacionadosPorGenero(String genero) {
        return repositorioLibro.buscarRelacionadosPorGenero(genero);
    }

    @Override
    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        return repositorioLibro.buscarLibrosPorTitulo(titulo);
    }

    @Override
    public List<Libro> buscarLibrosPorTituloYAutor(String busqueda) {
        List<Libro> libros;
        libros = repositorioLibro.buscarLibrosPorTitulo(busqueda);
        libros.addAll(repositorioLibro.buscarLibrosPorAutor(busqueda));
        return libros;
    }

    @Override
    public boolean verificarStock(Integer idLibro, Integer cantidadDelLibro) {
        Libro libro = repositorioLibro.buscarLibroPorId(idLibro);
        return libro.getCantidadEnStock() >= cantidadDelLibro;
    }

    @Override
    public List<Libro> devolverTodosLosLibros() {
        return repositorioLibro.devolverTodosLosLibros();
    }

    @Override
    public List<Libro> buscarLibroPorTituloALaVenta(String titulo) {
        return repositorioLibro.buscarLibroPorTituloALaVenta(titulo);
    }

}
