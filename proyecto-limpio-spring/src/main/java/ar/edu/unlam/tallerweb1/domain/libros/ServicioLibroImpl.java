package ar.edu.unlam.tallerweb1.domain.libros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicioLibroImpl implements ServicioLibro {

    private RepositorioLibro repositorioLibro;
    private ServletContext servletContext;

    @Autowired
    public ServicioLibroImpl (RepositorioLibro repositorioLibro, ServletContext servletContext) {
        this.repositorioLibro = repositorioLibro;
        this.servletContext = servletContext;
    }

    @Override
    public Integer crearLibro(Libro libro, MultipartFile imagen) {
        // Pregunto si la imagen que se subio esta vacia o es nula
        if(!imagen.isEmpty() && imagen != null) {
            // Guardo el libro y su imagen en la base de datos
            ImagenLibro imagenLibro = new ImagenLibro();
            libro.setImagen(imagenLibro);
            libro = repositorioLibro.guardarLibro(libro);
            // Guardo la imagen en el servidor
            try {
                String nombreDeLaImagen = servletContext.getRealPath("/") + "images"+ "\\" + libro.getImagen().getId() + ".jpg";
                imagen.transferTo(new File(nombreDeLaImagen));
            } catch (Exception e ){
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
        List<Libro> listaDeLibros = repositorioLibro.obtenerListaDeLibros();
        // Con esto se filtran solo los libros que tengan en true la propiedad a la venta
        //List<Libro> listaDeLibrosALaVenta = listaDeLibros.stream().filter((libro -> libro.getALaVenta())).collect(Collectors.toList());
        return listaDeLibros;
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
    public List<Libro> devolverTodosLosLibros() {
        return repositorioLibro.devolverTodosLosLibros();
    }

    @Override
    public List<Libro> buscarLibroPorTitulo(String titulo) {
        return repositorioLibro.buscarLibroPorTitulo(titulo);
    }


}
