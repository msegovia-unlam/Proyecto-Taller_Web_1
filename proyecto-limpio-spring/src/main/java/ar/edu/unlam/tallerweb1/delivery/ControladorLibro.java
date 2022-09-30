package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorLibro {

    private ServicioLibro servicioLibro;

    @Autowired
    public ControladorLibro(ServicioLibro servicioLibro) {
        this.servicioLibro = servicioLibro;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView irAHome() {
        ModelMap modelo = new ModelMap();
        List<Libro> librosALaVenta = servicioLibro.obtenerLibrosALaVenta();
        modelo.addAttribute("librosALaVenta", librosALaVenta);
        // TODO: modificar la vista de home para que muestre las imagenes de los libros
        return new ModelAndView("home", modelo);
    }

    @RequestMapping(path = "/libro/{idLibro}", method = RequestMethod.GET)
    public ModelAndView irALibro(@PathVariable Integer idLibro) {
        ModelMap modelo = new ModelMap();
        Libro libro = servicioLibro.buscarLibroPorId(idLibro);
        modelo.addAttribute("libro", libro);
        // TODO: modificar vista de detalles de un libro para mostrar la imagen
        return new ModelAndView("libro", modelo);
    }

    @RequestMapping(path = "/crear-libro", method = RequestMethod.GET)
    public ModelAndView irFormularioCrearLibro(Libro libro) {
        ModelMap modelo = new ModelMap();
        modelo.addAttribute("libro", libro);
        return new ModelAndView("form-crear-libro", modelo);
    }

    @RequestMapping(path = "/crear-libro", method = RequestMethod.POST)
    public ModelAndView crearLibro(Libro libro) {
        // TODO: agregar la carga de una imagen
        ModelMap modelo = new ModelMap();
        servicioLibro.guardarLibro(libro);
        return new ModelAndView("redirect:/", modelo);
    }

    @RequestMapping(value = "/modificar-libro/{id}", method = RequestMethod.GET)
    public ModelAndView modificarLibro(@PathVariable("id") Integer id) {
        ModelMap modelo = new ModelMap();
        Libro libro = servicioLibro.buscarLibroPorId(id);
        modelo.addAttribute("libro", libro);
        return new ModelAndView("form-modificar-libro", modelo);
    }

    @RequestMapping(path = "/modificar-libro/{id}", method = RequestMethod.POST)
    public ModelAndView salvarLibro(@PathVariable("id") Integer id, Libro libro) {
        System.out.println(libro);
        ModelMap modelo = new ModelMap();
        servicioLibro.modificarLibro(libro);
        return new ModelAndView("redirect:/libro/" + id, modelo);
    }

    @RequestMapping(path = "/borrar-libro/{id}", method = RequestMethod.POST)
    public ModelAndView eliminarLibro(@PathVariable("id") Integer id) {
        ModelMap modelo = new ModelMap();
        Libro libroABuscar = servicioLibro.buscarLibroPorId(id);
        if (libroABuscar != null) {
            servicioLibro.borrarLibro(libroABuscar);
        } else {
            modelo.put("error", "Ha ocurrido un error");
        }
        return new ModelAndView("redirect:/", modelo);
    }
}
