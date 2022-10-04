package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
import ar.edu.unlam.tallerweb1.domain.usuarios.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class ControladorAdmin {

    private ServicioLibro servicioLibro;
    private HttpServletRequest request;

    @Autowired
    public ControladorAdmin(ServicioLibro servicioLibro, HttpServletRequest request) {
        this.servicioLibro = servicioLibro;
        this.request = request;
    }

    @RequestMapping("/")
    public ModelAndView irAAdmin() {
        ModelMap modelo = new ModelMap();
        String vista;
        if (request.getSession().getAttribute("ROL") == Rol.ADMIN) {
            List<Libro> libros = servicioLibro.devolverTodosLosLibros();
            modelo.put("datosLibro", libros);
            vista = "admin";
        } else {
            vista = "redirect:/";
        }
        return new ModelAndView(vista, modelo);
    }

    @RequestMapping("/buscar")
    public ModelAndView buscarLibro(@RequestParam(name = "buscar", defaultValue = "") String titulo) {
        ModelMap modelo = new ModelMap();
        String vista;
        if (request.getSession().getAttribute("ROL") == Rol.ADMIN) {
            List<Libro> libros = servicioLibro.buscarLibroPorTitulo(titulo);
            modelo.put("datosLibro", libros);
            vista = "admin";
        } else {
            vista = "redirect:/";
        }
        return new ModelAndView(vista, modelo);
    }

    @RequestMapping(path = "/crear-libro", method = RequestMethod.GET)
    public ModelAndView irFormularioCrearLibro(Libro libro) {
        ModelMap modelo = new ModelMap();
        String vista;
        if (request.getSession().getAttribute("ROL") == Rol.ADMIN) {
            modelo.addAttribute("libro", libro);
            vista = "form-crear-libro";
        } else {
            vista = "redirect:/";
        }
        return new ModelAndView(vista, modelo);
    }

    @RequestMapping(path = "/crear-libro", method = RequestMethod.POST)
    public ModelAndView crearLibro(Libro libro, MultipartFile file, RedirectAttributes redirectAttributes) {
        ModelMap modelo = new ModelMap();
        String vista;
        if (request.getSession().getAttribute("ROL") == Rol.ADMIN) {
            Integer idLibroNuevo = servicioLibro.crearLibro(libro, file);
            if (idLibroNuevo != null) {
                redirectAttributes.addFlashAttribute("libroCreado", "El libro se ha creado correctamente");
            } else {
                redirectAttributes.addFlashAttribute("libroNoCreado", "Hubo un error con la creación del libro");
            }
            vista = "redirect:/admin/";
        } else {
            vista = "redirect:/";
        }
        return new ModelAndView(vista, modelo);
    }

    @RequestMapping(path = "/borrar-libro/{id}", method = RequestMethod.POST)
    public ModelAndView eliminarLibro(@PathVariable("id") Integer id) {
        ModelMap modelo = new ModelMap();
        String vista;
        if(request.getSession().getAttribute("ROL") == Rol.ADMIN) {
            Libro libroABuscar = servicioLibro.buscarLibroPorId(id);
            if (libroABuscar != null) {
                servicioLibro.borrarLibro(libroABuscar);
                modelo.put("mensajeExitoso", "El libro se borró correctamente");
            } else {
                modelo.put("mensajeError", "Ha ocurrido un error");
            }
            vista = "admin";
        } else {
            vista = "redirect:/";
        }
        return new ModelAndView(vista, modelo);
    }

}
