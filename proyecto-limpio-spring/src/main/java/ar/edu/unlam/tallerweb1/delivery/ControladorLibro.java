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


@Controller
public class ControladorLibro {

    private ServicioLibro servicioLibro;

    @Autowired
    public ControladorLibro(ServicioLibro servicioLibro){
        this.servicioLibro=servicioLibro;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView irAHome() {
        ModelMap modelo = new ModelMap();
        return new ModelAndView("home", modelo);
    }

    @RequestMapping(path = "/libro", method = RequestMethod.GET)
    public ModelAndView irALibro() {
        ModelMap modelo = new ModelMap();
        return new ModelAndView("libro", modelo);
    }

    @RequestMapping(value = "/modificar-libro/{id}", method = RequestMethod.GET)
    public ModelAndView modificarLibro(@PathVariable("id") long id){

        ModelMap modelo = new ModelMap();
        modelo.put("id", id);

        return new ModelAndView("modificar-libro", modelo);

    }

    @RequestMapping(path = "/crear-libro", method = RequestMethod.GET)
    public ModelAndView irFormularioCrearLibro(Libro libro) {

        ModelMap modelo = new ModelMap();
        modelo.addAttribute("libro", libro);

        return new ModelAndView("form-crear-libro", modelo);
    }



    @RequestMapping (path = "/borrar-libro")
    public ModelAndView eliminarLibro(){
        ModelMap modelo = new ModelMap();
        Libro libroABuscar=servicioLibro.buscarLibroPorId(1L);
        if (libroABuscar!=null) {
            servicioLibro.borrarLibro(libroABuscar);
            modelo.put("libroBorrado", "El libro se borró correctamente");
        }else{
            modelo.put("error", "Ha ocurrido un error");
        }
        return new ModelAndView("libro", modelo);

    }

}
