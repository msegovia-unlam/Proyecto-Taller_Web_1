package ar.edu.unlam.tallerweb1.domain.libros;

import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioLibroImpl;
import org.junit.Test;

import javax.servlet.ServletContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ServicioLibroTest {

    RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
    RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    ServletContext servletContext = mock(ServletContext.class);
    ServicioLibro servicioLibro = new ServicioLibroImpl(repositorioLibro, servletContext, repositorioUsuario);

//    @Test
//    public void siElLibroNoTieneImagenNoSeCrea() {
//
//        // preparacion
//        //givenLibro();
//
//        // ejecucion
//        Libro libro = new Libro();
//        Integer idCreado = whenCrearLibro(libro);
//
//        // comprobacion
//        thenLaCreacionFalla(idCreado);
//
//    }
//
//    private void thenLaCreacionFalla(Integer idCreado) {
//        assertThat(idCreado).isNull();
//    }
//
//    private Integer whenCrearLibro(Libro libro) {
//        // la imagen vacia se simula con un nulo
//        return servicioLibro.crearLibro(libro, null);
//    }

}
