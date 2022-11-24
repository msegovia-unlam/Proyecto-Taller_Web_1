package ar.edu.unlam.tallerweb1.delivery;
import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
import ar.edu.unlam.tallerweb1.domain.usuarios.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


public class ControladorLibroTest {

    ServicioLibro servicioLibro = mock(ServicioLibro.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    ControladorLibro controladorLibro = new ControladorLibro(servicioLibro, request);
    RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);


    HttpSession session = mock(HttpSession.class);


    @Test
    public void sePuedaCalificarUnLibro(){


        //preparadcion => given
        givenSeBuscaLibroACalificar();

        //ejecucion => when
        Integer idLibro = 1;
        Integer calificacion = 3;

        session.setAttribute("USUARIO_ID", 1);
        when(request.getSession()).thenReturn(session);

        ModelAndView mav = whenSeCalificaElLibro(idLibro, calificacion);

        //comprobacion =>
        thenLaCalificacionFueExitosa(mav);

    }

    private void givenSeBuscaLibroACalificar() {
    }

    private ModelAndView whenSeCalificaElLibro(Integer idLibro, Integer calificacion) {

        return controladorLibro.calificarLibro(idLibro, calificacion);
    }

    private void thenLaCalificacionFueExitosa(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("redirect:/libro/1");
    }


    @Test
    public void queSePuedaIrAUnLibro(){

        Integer idLibro = 1;

        when(servicioLibro.buscarLibroPorId(idLibro)).thenReturn(new Libro());
        ModelAndView mav = controladorLibro.irALibro(idLibro);

        assertThat(mav.getViewName()).isEqualTo("libro");

    }

    @Test
    public void queSePuedaComprarUnLibro(){

        Integer idLibro = 2;

        session.setAttribute("USUARIO_ID", 1);
        session.setAttribute("ROL", Rol.NO_ADMIN);
        when(request.getSession()).thenReturn(session);

        when(servicioLibro.comprarLibro(idLibro, 1)).thenReturn(true);
        when(request.getSession().getAttribute("ROL")).thenReturn(Rol.NO_ADMIN);
        ModelAndView mav = controladorLibro.comprarLibro(idLibro, redirectAttributes);

        assertThat(mav.getViewName()).isEqualTo("redirect:/libro/" + idLibro);

    }

    @Test
    public void libroPorAutor(){

        String autor = "Carlitos";

        when(servicioLibro.buscarLibroPorAutor(autor)).thenReturn(new ArrayList<Libro>());

        ModelAndView  mav = controladorLibro.libroPorAutor(autor);

        assertThat(mav.getViewName()).isEqualTo("libros-por-autor");
    }




}
