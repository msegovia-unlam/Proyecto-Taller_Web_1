package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;


@Controller
public class ControladorLibro {

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

}
