package ar.edu.unlam.tallerweb1.delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRedSocial{

//    @Autowired
//    public ControladorRedSocial() {
//
//    }

    @RequestMapping("red-social")
    public ModelAndView irARedSocial(){
        ModelMap modelo = new ModelMap();
        return new ModelAndView("red-social/home",modelo);
    }

}
