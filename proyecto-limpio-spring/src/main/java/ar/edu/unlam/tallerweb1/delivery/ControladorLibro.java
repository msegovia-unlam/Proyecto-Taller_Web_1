package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
import ar.edu.unlam.tallerweb1.domain.usuarios.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorLibro {

    private ServicioLibro servicioLibro;
    private HttpServletRequest request;


    @Autowired
    public ControladorLibro(ServicioLibro servicioLibro, HttpServletRequest request) {
        this.servicioLibro = servicioLibro;
        this.request = request;
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
        List<Libro> librosRelacionados = servicioLibro.buscarRelacionadosPorAutor(libro.getAutor());
        List<Libro> librosDelMismoGenero = servicioLibro.buscarRelacionadosPorGenero(libro.getGenero());

        modelo.addAttribute("relacionadosPorGenero", librosDelMismoGenero);
        modelo.addAttribute("libro", libro);
        modelo.addAttribute("relacionados", librosRelacionados);
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

    @RequestMapping(value = "/buscar-autor/{autor}", method = RequestMethod.GET)
    public ModelAndView libroPorAutor(@PathVariable String autor) {
        ModelMap modelo = new ModelMap();
        List<Libro> libros = servicioLibro.buscarLibroPorAutor(autor);
        modelo.put("librosPorAutor", libros);
        return new ModelAndView("libros-por-autor", modelo);
    }

    // Posible refactor a una clase de controlador de la compra

    @RequestMapping(path = "/comprar/{idLibro}", method = RequestMethod.GET)
    public ModelAndView comprarLibro(@PathVariable Integer idLibro, RedirectAttributes redirectAttributes) {
        ModelMap modelo = new ModelMap();
        String vista;
        if (request.getSession().getAttribute("ROL") == Rol.NO_ADMIN) {
            boolean exitoCompra = servicioLibro.comprarLibro(idLibro);
            if(exitoCompra)
                redirectAttributes.addFlashAttribute("msj", "¡La compra se realizó con exito!");
            else
                redirectAttributes.addFlashAttribute("msj", "Hubo un error en la compra.");
            vista = "redirect:/libro/" + idLibro;
        } else if (request.getSession().getAttribute("ROL") == null) {
             redirectAttributes.addFlashAttribute("error", "Debes estar logeado para comprar un libro");
            vista = "redirect:/login";
        } else {
            vista = "redirect:/";
        }
        return new ModelAndView(vista, modelo);
    }

}
