package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
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
        return new ModelAndView("home", modelo);
    }

    @RequestMapping(path = "/libro/{idLibro}", method = RequestMethod.GET)
    public ModelAndView irALibro(@PathVariable Integer idLibro) {
        ModelMap modelo = new ModelMap();
        Libro libro = servicioLibro.buscarLibroPorId(idLibro);
        modelo.addAttribute("libro", libro);
        return new ModelAndView("libro", modelo);
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
    public ModelAndView eliminarLibro(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ModelMap modelo = new ModelMap();
        Libro libroABuscar = servicioLibro.buscarLibroPorId(id);
        if (libroABuscar != null) {
            servicioLibro.borrarLibro(libroABuscar);
            redirectAttributes.addFlashAttribute("mensajeExitoso", "El libro se borró correctamente");
        } else {
            redirectAttributes.addFlashAttribute("mensajeError", "Ha ocurrido un error");
        }
        return new ModelAndView("redirect:/admin", modelo);
    }

    @RequestMapping("/buscar-home")
    public ModelAndView buscarLibroHome(@RequestParam(name = "buscar") String titulo){
        ModelMap modelo = new ModelMap();
        List<Libro> libros = servicioLibro.buscarLibroPorTitulo(titulo);
        modelo.put("librosALaVenta", libros);
        return new ModelAndView("home", modelo);
    }

    @RequestMapping(value = "/modificar-libro/{id}", method = RequestMethod.POST)
    public ModelAndView modificarLibro(@PathVariable("id") Integer id){

        ModelMap modelo = new ModelMap();
        Libro libroAEditar = servicioLibro.buscarLibroPorId(id);

        if (libroAEditar != null){
            modelo.put("libro", libroAEditar);
            return new ModelAndView("modificar-libro", modelo);

        }else{
            modelo.put("error", "Libro no encontrado");
        }
        return new ModelAndView("home", modelo);
    }

    @RequestMapping(path = "/actualizarLibro", method = RequestMethod.POST)
    public ModelAndView actualizarLibro(@ModelAttribute("libro") Libro libroAActualizar, HttpServletRequest request){
        ModelMap modelo = new ModelMap();

        servicioLibro.actualizarLibro(libroAActualizar);

        modelo.put("libro", libroAActualizar);

        return new ModelAndView("libro", modelo);

    }
}
