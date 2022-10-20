package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping("/buscar-home")
    public ModelAndView buscarLibroHome(@RequestParam(name = "buscar") String titulo, RedirectAttributes redirectAttributes){
        ModelMap modelo = new ModelMap();
        List<Libro> libros = servicioLibro.buscarLibroPorTitulo(titulo);
        if( libros.size()==0){
            modelo.put("sinLibros", "No se ha encontrado ningun libro");
        }
        modelo.put("librosALaVenta", libros);
        return new ModelAndView("home", modelo);
    }

    @RequestMapping("/novedades")
    public ModelAndView irANovedades(){
        ModelMap modelo = new ModelMap();
        List<Libro> librosEnNovedad = servicioLibro.obtenerLibrosEnNovedad();
        modelo.addAttribute("librosEnNovedad", librosEnNovedad);
        return new ModelAndView("novedades", modelo);
    }





}
