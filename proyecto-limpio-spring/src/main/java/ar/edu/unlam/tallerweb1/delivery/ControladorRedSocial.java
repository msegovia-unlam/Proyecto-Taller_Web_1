package ar.edu.unlam.tallerweb1.delivery;
import ar.edu.unlam.tallerweb1.domain.Follows.Follows;
import ar.edu.unlam.tallerweb1.domain.Follows.ServicioFollows;
import ar.edu.unlam.tallerweb1.domain.Publicacion.Publicacion;
import ar.edu.unlam.tallerweb1.domain.Publicacion.ServicioPublicacion;
import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
import ar.edu.unlam.tallerweb1.domain.usuarios.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/red-social/")
public class ControladorRedSocial{

    private ServicioLibro servicioLibro;
    private ServicioUsuario servicioUsuario;
    private HttpServletRequest request;
    private ServicioLogin servicioLogin;
    private ServicioRegistro servicioRegistro;
    private ServicioPublicacion servicioPublicacion;
    private ServicioFollows servicioFollows;

    @Autowired
    public ControladorRedSocial(ServicioLibro servicioLibro, ServicioUsuario servicioUsuario,
                                HttpServletRequest request, ServicioLogin servicioLogin,
                                ServicioRegistro servicioRegistro, ServicioPublicacion servicioPublicacion,
                                ServicioFollows servicioFollows) {
        this.servicioLibro = servicioLibro;
        this.servicioUsuario = servicioUsuario;
        this.request=request;
        this.servicioLogin=servicioLogin;
        this.servicioRegistro=servicioRegistro;
        this.servicioPublicacion=servicioPublicacion;
        this.servicioFollows=servicioFollows;
    }

    @RequestMapping("/")
    public ModelAndView irARedSocial(@ModelAttribute("datosLogin") DatosLogin datosLogin, RedirectAttributes redirectAttributes){
        ModelMap modelo = new ModelMap();
        String vista;

//        servicioPublicacion.getPublicaciones(seguidos);

        if (request.getSession().getAttribute("ROL") != null) {
            Integer usuarioId = (Integer) request.getSession().getAttribute("USUARIO_ID");
            List<Usuario> usuariosSeguidos = servicioFollows.getUsuariosSeguidos(usuarioId);

            List<Publicacion> publicaciones = servicioPublicacion.getPublicaciones(usuariosSeguidos);

            redirectAttributes.addFlashAttribute("publicacion", usuariosSeguidos);
            modelo.addAttribute("datosPublicacion", new Publicacion());
            vista = "red-social/home";
        } else {
            vista = "red-social/login";
        }
        return new ModelAndView(vista,modelo);
    }

    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        String vista;
        if(request.getSession().getAttribute("ROL") == null) {
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
        if(request.getSession().getAttribute("ROL") == null) {
            modelo.put("datosRegistro", new DatosRegistro());
            vista = "red-social/registro";
        } else {
            vista = "redirect:/red-social/";
        }
        return new ModelAndView(vista, modelo);
    }

    @RequestMapping(path = "/validar-registro", method = RequestMethod.POST)
    public ModelAndView validarRegistro(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro) {
        ModelMap model = new ModelMap();
        String vista;
        if(request.getSession().getAttribute("ROL") == null) {
            Usuario usuarioBuscado = servicioRegistro.consultarUsuario(datosRegistro.getEmail(), datosRegistro.getUsuarioName());
            if (usuarioBuscado != null) {
                model.put("error", "El usuario ya existe");
            } else {
                usuarioBuscado = new Usuario();
                usuarioBuscado.setEmail(datosRegistro.getEmail());
                usuarioBuscado.setNombre(datosRegistro.getUsuarioName());
                usuarioBuscado.setPassword(datosRegistro.getPassword());
                usuarioBuscado.setRol(Rol.NO_ADMIN);
                servicioRegistro.almacenarUsuario(usuarioBuscado);
                model.put("exitoso", "Se ha registrado correctamente");
            }
            vista = "red-social/registro";
        } else {
            vista = "redirect:/red-social/";
        }
        return new ModelAndView(vista, model);
    }

    @RequestMapping("/busqueda")
    public ModelAndView busqueda(@RequestParam(value = "busquedaLibro", defaultValue = "") String busquedaLibro,
                                 @RequestParam(value = "busquedaUsuario", defaultValue = "") String busquedaPersona) {
        ModelMap modelo = new ModelMap();

        if(!busquedaLibro.isEmpty() && busquedaPersona.isEmpty()) {
            if(busquedaLibro.equals(" ")) {
                modelo.addAttribute("mensajeError", "La busqueda no puede ir vacia");
                return new ModelAndView("red-social/busqueda-global", modelo);
            } else {
                Set<Libro> libros = new HashSet<>(servicioLibro.buscarLibrosPorTituloYAutor(busquedaLibro));
                modelo.addAttribute("libros", libros);
            }
        } else if(!busquedaPersona.isEmpty() && busquedaLibro.isEmpty()) {
            if(busquedaPersona.equals(" ")) {
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


    @RequestMapping("/agregar-publicacion")
    public ModelAndView agregarPublicacion(@ModelAttribute("datosPublicacion") DatosPublicacion datosPublicacion){
        ModelMap modelo = new ModelMap();
        LocalDateTime fecha = LocalDateTime.now();

        Integer usuarioId = (Integer) request.getSession().getAttribute("USUARIO_ID");
        Usuario usuario = servicioLogin.buscarUsuarioPorId(usuarioId);
        servicioPublicacion.crearPublicacion(datosPublicacion.getPublicacion(),fecha,
                usuario);


        return new ModelAndView("redirect:/red-social/",modelo);

    }


}
