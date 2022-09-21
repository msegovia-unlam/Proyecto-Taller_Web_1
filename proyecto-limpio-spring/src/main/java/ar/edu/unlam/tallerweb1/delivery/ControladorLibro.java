package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

        // Creo una lista de libros y luego los guardo en el modelo
        List<Libro> libros = Arrays.asList(
                new Libro(
                        "El señor de los anillos",
                        1000,
                        "J. R. R. Tolkien",
                        "Fantasia"),
                new Libro(
                        "Crimen y Castigo",
                        500,
                        "Dostoyevski",
                        "Psicologico"),
                new Libro(
                        "Harry Potter",
                        600,
                        "JK Rowling",
                        "Fantasia"
                ),
                new Libro(
                        "Harry Potter",
                        600,
                        "JK Rowling",
                        "Fantasia"
                ));
        modelo.put("libros", libros);

        return new ModelAndView("home", modelo);
    }

    @RequestMapping(path = "/libro", method = RequestMethod.GET)
    public ModelAndView irALibro() {
        ModelMap modelo = new ModelMap();

        return new ModelAndView("libro", modelo);
    }

    @RequestMapping(path = "/crear-libro", method = RequestMethod.GET)
    public ModelAndView irFormularioCrearLibro() {
        ModelMap modelo = new ModelMap();

        return new ModelAndView("form-crear-libro", modelo);
    }

}
