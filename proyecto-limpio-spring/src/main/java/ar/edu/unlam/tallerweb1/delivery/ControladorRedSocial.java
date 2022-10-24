package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
import ar.edu.unlam.tallerweb1.domain.usuarios.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/red-social/")
public class ControladorRedSocial {

    private ServicioLibro servicioLibro;
    private ServicioUsuario servicioUsuario;
    private HttpServletRequest request;
    private ServicioLogin servicioLogin;

    @Autowired
    public ControladorRedSocial(ServicioLibro servicioLibro, ServicioUsuario servicioUsuario, HttpServletRequest request, ServicioLogin servicioLogin) {
        this.servicioLibro = servicioLibro;
        this.servicioUsuario = servicioUsuario;
        this.request = request;
        this.servicioLogin = servicioLogin;
    }

    @RequestMapping("/")
    public ModelAndView irARedSocial(@ModelAttribute DatosLogin datosLogin) {
        ModelMap modelo = new ModelMap();
        String vista;
        if (request.getSession().getAttribute("ROL") != null) {
            vista = "red-social/home";
        } else {
            vista = "red-social/login";
        }
        return new ModelAndView(vista, modelo);
    }

    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        String vista;
        if (request.getSession().getAttribute("ROL") == null) {
            // invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
            // hace una llamada a otro action a traves de la URL correspondiente a esta
            Usuario usuarioBuscado = servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());
            if (usuarioBuscado != null) {
                request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
                request.getSession().setAttribute("USUARIO_ID", usuarioBuscado.getId());
                vista = "redirect:/red-social/";
            } else {
                // si el usuario no existe agrega un mensaje de error en el modelo.
                model.put("error", "Usuario o clave incorrecta");
                vista = "red-social/login";
            }
        } else {
            vista = "redirect:/";
        }
        return new ModelAndView(vista, model);
    }

    @RequestMapping(path = "/registro", method = RequestMethod.GET)
    ModelAndView irAlRegistro() {
        ModelMap modelo = new ModelMap();
        String vista;
        if (request.getSession().getAttribute("ROL") == null) {
            modelo.put("datosRegistro", new DatosRegistro());
            vista = "red-social/registro";
        } else {
            vista = "redirect:/red-social/";
        }
        return new ModelAndView(vista, modelo);
    }

    @RequestMapping("/busqueda")
    public ModelAndView busqueda(@RequestParam(value = "busquedaLibro", defaultValue = "") String busquedaLibro,
                                 @RequestParam(value = "busquedaUsuario", defaultValue = "") String busquedaPersona) {
        ModelMap modelo = new ModelMap();

        if (!busquedaLibro.isEmpty() && busquedaPersona.isEmpty()) {
            if (busquedaLibro.equals(" ")) {
                modelo.addAttribute("mensajeError", "La busqueda no puede ir vacia");
                return new ModelAndView("red-social/busqueda-global", modelo);
            } else {
                Set<Libro> libros = new HashSet<>(servicioLibro.buscarLibrosPorTituloYAutor(busquedaLibro));
                modelo.addAttribute("libros", libros);
            }
        } else if (!busquedaPersona.isEmpty() && busquedaLibro.isEmpty()) {
            if (busquedaPersona.equals(" ")) {
                modelo.addAttribute("mensajeError", "La busqueda no puede ir vacia");
                return new ModelAndView("red-social/busqueda-global", modelo);
            } else {
                Set<Usuario> usuarios = new HashSet<>(servicioUsuario.obtenerUsuariosPorNombre(busquedaPersona));
                modelo.addAttribute("usuarios", usuarios);
            }
        } else {
            modelo.addAttribute("mensajeError", "La busqueda no puede ir vacia");
            return new ModelAndView("red-social/busqueda-global", modelo);
        }

        return new ModelAndView("red-social/busqueda-global", modelo);
    }

    @RequestMapping("/my-books")
    public ModelAndView irAMyBooks() {
        ModelMap modelo = new ModelMap();
        String vista;
        if (request.getSession().getAttribute("ROL") != null) {
            Integer idUsuario = (Integer) request.getSession().getAttribute("USUARIO_ID");
            Set<Libro> librosComprados = new HashSet<>(servicioLibro.obtenerLibrosComprados(idUsuario));
            modelo.addAttribute("librosComprados", librosComprados);
            vista = "red-social/my-books";
        } else {
            vista = "redirect:/red-social/";
        }
        return new ModelAndView(vista, modelo);
    }

}
