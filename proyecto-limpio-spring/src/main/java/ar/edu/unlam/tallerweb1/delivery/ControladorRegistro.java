package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistro;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorRegistro {

    private ServicioRegistro servicioRegistro;

    @Autowired
    public ControladorRegistro(ServicioRegistro servicioRegistro) {
        this.servicioRegistro = servicioRegistro;
    }

    @RequestMapping(path = "/registro", method = RequestMethod.GET)
    ModelAndView irAlRegistro() {
        ModelMap modelo = new ModelMap();
        modelo.put("datosRegistro", new DatosRegistro());
        return new ModelAndView("registro", modelo);
    }

    @RequestMapping(path = "/validar-registro", method = RequestMethod.POST)
    public ModelAndView validarRegistro(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro) {
        ModelMap model = new ModelMap();

        Usuario usuarioBuscado = servicioRegistro.consultarUsuario(datosRegistro.getEmail(), datosRegistro.getUsuarioName());
        if (usuarioBuscado != null) {
            model.put("error", "El usuario ya existe");
        } else {
            usuarioBuscado = new Usuario();
            usuarioBuscado.setEmail(datosRegistro.getEmail());
            usuarioBuscado.setNombre(datosRegistro.getUsuarioName());
            usuarioBuscado.setPassword(datosRegistro.getPassword());

            servicioRegistro.almacenarUsuario(usuarioBuscado);
            model.put("exitoso", "Se ha registrado correctamente");
            return new ModelAndView("registro", model);
        }
        return new ModelAndView("registro", model);
    }

}
