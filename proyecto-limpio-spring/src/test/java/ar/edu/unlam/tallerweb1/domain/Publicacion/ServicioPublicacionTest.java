package ar.edu.unlam.tallerweb1.domain.Publicacion;

import ar.edu.unlam.tallerweb1.domain.Encuesta.Encuesta;
import ar.edu.unlam.tallerweb1.domain.Votacion.VotacionesTotales;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import junit.framework.TestCase;
import org.junit.Test;

import javax.servlet.ServletContext;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioPublicacionTest{


    RepositorioPublicacion repositorioPublicacion = mock(RepositorioPublicacion.class);
    ServletContext servletContext = mock(ServletContext.class);
    ServicioPublicacion servicioPublicacion = new ServicioPublicacionImpl(repositorioPublicacion, servletContext);

    @Test
    public void queObtengaTodasLasPublicaciones() {

        Usuario usuario = new Usuario();
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);

        when(repositorioPublicacion.getPublicaciones(usuario)).thenReturn(new ArrayList<Publicacion>());

        List<Publicacion> publicaciones = servicioPublicacion.getPublicaciones(usuarios);

        assertThat(publicaciones).isNotNull();

    }

    @Test
    public void testObtenerVotosTotales() {

        Integer encuestaId = 1;

        when(repositorioPublicacion.obtenerVotosTotales(encuestaId)).thenReturn(new ArrayList<VotacionesTotales>());

        List<VotacionesTotales> votaciones = servicioPublicacion.obtenerVotosTotales(encuestaId);

        assertThat(votaciones).isNotNull();
    }


    @Test
    public void queSePuedaVerificarVotoDoble(){
        Integer encuestaId = 1;
        Usuario usuario = new Usuario();
        Encuesta encuesta = new Encuesta();

        when(repositorioPublicacion.buscarEncuestaPorId(encuestaId)).thenReturn( encuesta);
        when(repositorioPublicacion.verificarDobleVoto(usuario, encuesta)).thenReturn(true);

        Boolean verificacion = servicioPublicacion.verificarDobleVoto(usuario, encuestaId);

        assertThat(verificacion).isTrue();

    }


}