package ar.edu.unlam.tallerweb1.domain.libros;

import ar.edu.unlam.tallerweb1.domain.Calificacion.Calificacion;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioLibroImpl;
import org.junit.Test;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioLibroTest {

    RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
    RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    ServletContext servletContext = mock(ServletContext.class);
    ServicioLibro servicioLibro = new ServicioLibroImpl(repositorioLibro, servletContext, repositorioUsuario);


    @Test
    public void seBuscaLibroPorTitulo(){

        String titulo = "abd";

        when(repositorioLibro.buscarLibrosPorTitulo(titulo)).thenReturn(new ArrayList<Libro>());

        List<Libro> libros = servicioLibro.buscarLibrosPorTitulo(titulo);

        assertThat(libros).isNotNull();
    }

    @Test
    public void siElLibroNoTieneImagenNoSeCrea() {

        // preparacion
        //givenLibro();

        // ejecucion
        Libro libro = new Libro();
        Integer idCreado = whenCrearLibro(libro);

        // comprobacion
        thenLaCreacionFalla(idCreado);

    }

    private void thenLaCreacionFalla(Integer idCreado) {
        assertThat(idCreado).isNull();
    }

    private Integer whenCrearLibro(Libro libro) {
        // la imagen vacia se simula con un nulo
        return servicioLibro.crearLibro(libro, null);
    }


    @Test
    public void queSePuedaObtenerUsuariosQueHayanCalificadoUnLibro(){

        // preparacion
        Integer idLibro = 1;
        Integer calificacion = 4;
        Integer idUsuario = 2;

        // ejecucion
        servicioLibro.calificarLibro(idLibro, calificacion,idUsuario);
        Integer idUsuario2 = servicioLibro.obtenerUsuariosQueCalificarionUnLibro(idLibro);

        // comprobacion
        assertThat(idUsuario2).isNotNull();
    }

    @Test
    public void queSePuedaComprarUnLibroEnElServicio(){

        Integer idUsuario = 1;
        Integer idLibro = 3;
        Libro libro = new Libro();
        libro.setCantidadEnStock(5);


        when(repositorioLibro.buscarLibroPorId(3)).thenReturn(libro);
        when(repositorioLibro.reducirStock(3)).thenReturn(true);


        Boolean compra = servicioLibro.comprarLibro(idLibro, idUsuario );

        assertThat(compra).isTrue();

    }









}
