package ar.edu.unlam.tallerweb1.delivery;
import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/red-social/")
public class ControladorRedSocial{

    private ServicioLibro servicioLibro;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorRedSocial(ServicioLibro servicioLibro, ServicioUsuario servicioUsuario) {
        this.servicioLibro = servicioLibro;
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping("/")
    public ModelAndView irARedSocial(){
        ModelMap modelo = new ModelMap();
        return new ModelAndView("red-social/home",modelo);
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

}
