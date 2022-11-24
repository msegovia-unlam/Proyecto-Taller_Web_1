package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.Follows.ServicioFollows;
import ar.edu.unlam.tallerweb1.domain.Publicacion.Publicacion;
import ar.edu.unlam.tallerweb1.domain.Publicacion.ServicioPublicacion;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
import ar.edu.unlam.tallerweb1.domain.usuarios.*;
import junit.framework.TestCase;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorRedSocialTest extends TestCase {

    HttpServletRequest request = mock(HttpServletRequest.class);
    RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
    HttpSession session = mock(HttpSession.class);

    ServicioLogin servicioLogin = mock(ServicioLogin.class);
    ServicioLibro servicioLibro = mock(ServicioLibro.class);
    ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
    ServicioRegistro  servicioRegistro = mock(ServicioRegistro.class);
    ServicioPublicacion servicioPublicacion = mock(ServicioPublicacion.class);
    ServicioFollows servicioFollows = mock(ServicioFollows.class);


    ControladorRedSocial controladorRedSocial = new ControladorRedSocial(servicioLibro, servicioUsuario, request, servicioLogin, servicioRegistro, servicioPublicacion, servicioFollows);



    public void testIrARedSocial() {

        DatosLogin datosLogin = new DatosLogin();
        List<Usuario> usuarios = new ArrayList<>();

        datosLogin.setEmail("prueba@prueba");
        datosLogin.setPassword("1234");

        session.setAttribute("USUARIO_ID", 1);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("ROL")).thenReturn(Rol.NO_ADMIN);
        when(servicioFollows.getUsuariosSeguidos(1)).thenReturn(usuarios);
        when(servicioPublicacion.getPublicaciones(usuarios)).thenReturn(new ArrayList<Publicacion>());

        ModelAndView mav = controladorRedSocial.irARedSocial(datosLogin, null, redirectAttributes);

        assertThat(mav.getViewName()).isEqualTo("red-social/home");


    }

    public void testAgregarPublicacion() {

    }

    public void testVerPerfilDeUsuario() {
    }

    public void testVerDetallesLibro() {
    }
}