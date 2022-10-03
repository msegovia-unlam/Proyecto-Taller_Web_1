package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(path = "/crear-libro", method = RequestMethod.GET)
    public ModelAndView irFormularioCrearLibro(Libro libro) {
        ModelMap modelo = new ModelMap();
        modelo.addAttribute("libro", libro);
        return new ModelAndView("form-crear-libro", modelo);
    }

    @RequestMapping(path = "/crear-libro", method = RequestMethod.POST)
    public ModelAndView crearLibro(Libro libro, MultipartFile file) {
        ModelMap modelo = new ModelMap();
        Integer idLibroNuevo = servicioLibro.crearLibro(libro, file);
        if (idLibroNuevo!=null){
            modelo.put("libroCreado", "El libro se ha creado correctamente");
        }else{
            modelo.put("libroNoCreado", "Hubo un error con la creación del libro");
        }
        return new ModelAndView("redirect:/admin", modelo);
    }


    @RequestMapping(path = "/borrar-libro", method = RequestMethod.POST)
    public ModelAndView eliminarLibro(@ModelAttribute("libroId") DatosLibro libroId) {
        ModelMap modelo = new ModelMap();
        Libro libroABuscar = servicioLibro.buscarLibroPorId(libroId.getId());
        if (libroABuscar != null) {
            servicioLibro.borrarLibro(libroABuscar);
            modelo.put("mensajeExitoso", "El libro se borró correctamente");
        } else {
            modelo.put("mensajeError", "Ha ocurrido un error");
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
