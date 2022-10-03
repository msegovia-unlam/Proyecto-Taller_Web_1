package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorAdmin {

    ServicioLibro servicioLibro;

    @Autowired
    public ControladorAdmin(ServicioLibro servicioLibro){
        this.servicioLibro=servicioLibro;
    }

    @RequestMapping("/admin")
    public ModelAndView irAAdmin(){
        ModelMap modelo = new ModelMap();
        List<Libro> libros = servicioLibro.devolverTodosLosLibros();
        modelo.put("datosLibro", libros);
        return new ModelAndView("admin", modelo);
    }
    @RequestMapping("/buscar")
    public ModelAndView buscarLibro(@RequestParam (name = "buscar") String titulo){
        ModelMap modelo = new ModelMap();
        List<Libro> libros = servicioLibro.buscarLibroPorTitulo(titulo);
        modelo.put("datosLibro", libros);
        return new ModelAndView("admin", modelo);
    }

    @RequestMapping("/cambiar-estado-venta/{id}")
    public ModelAndView cambiarEstadoDeVentaDelLibro(@PathVariable("id") Integer id){
        ModelMap modelo = new ModelMap();
        servicioLibro.cambiarEstadoDeVentaDelLibro(id);
        return new ModelAndView("redirect:/admin",modelo);
    }

}
