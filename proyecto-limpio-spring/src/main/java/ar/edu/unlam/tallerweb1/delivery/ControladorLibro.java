package ar.edu.unlam.tallerweb1.delivery;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControladorLibro {

    @RequestMapping("/libro")
    public ModelAndView irALibro() {
        ModelMap modelo = new ModelMap();

        modelo.put("datosLibro", new Libro());

        return new ModelAndView("libro", modelo);
    }
}
