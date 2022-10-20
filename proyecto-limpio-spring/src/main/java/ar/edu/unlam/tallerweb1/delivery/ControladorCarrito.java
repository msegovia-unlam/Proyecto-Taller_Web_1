package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.Carrito.ServicioCarrito;
import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ControladorCarrito {

    private ServicioLibro servicioLibro;
    private HttpServletRequest request;
    private ServicioLogin servicioLogin;
    private ServicioCarrito servicioCarrito;


    @Autowired
    public ControladorCarrito(ServicioLibro servicioLibro, HttpServletRequest request, ServicioLogin servicioLogin, ServicioCarrito servicioCarrito) {
        this.servicioLibro = servicioLibro;
        this.request = request;
        this.servicioLogin = servicioLogin;
        this.servicioCarrito = servicioCarrito;
    }

    @RequestMapping("/carrito")
    public ModelAndView irACarrito() {
        ModelMap modelo = new ModelMap();
        String vista;
        Object usuarioRol = request.getSession().getAttribute("ROL");

        if (usuarioRol != null) {
            Integer usuarioId = (Integer) request.getSession().getAttribute("USUARIO_ID");
            Set<Libro> listaDeLibrosDelCarrito = new HashSet<>(servicioCarrito.obtenerListaDeIdDeLibrosDelCarrito(usuarioId));
            if (listaDeLibrosDelCarrito.size() != 0) {
                modelo.put("carrito", listaDeLibrosDelCarrito);
            } else {
                modelo.put("carritoVacio", "No tienen ningun libro en el carrito");
            }
            vista = "carrito";
        } else {
            vista = "redirect:/";
        }
        return new ModelAndView(vista, modelo);
    }

    @RequestMapping(value = "/agregar-a-carrito/{libroId}", method = RequestMethod.POST)
    public ModelAndView agregarACarrito(@PathVariable Integer libroId, RedirectAttributes redirectAttributes) {
        ModelMap modelo = new ModelMap();
        Object usuarioRol = request.getSession().getAttribute("ROL");

        if (usuarioRol != null) {
            Libro libro = servicioLibro.buscarLibroPorId(libroId);
            Integer usuarioId = (Integer) request.getSession().getAttribute("USUARIO_ID");
            Usuario usuario = servicioLogin.buscarUsuarioPorId(usuarioId);
            servicioCarrito.agregarLibroAlCarrito(usuario, libro);
            redirectAttributes.addFlashAttribute("libroAgregado", "El libro se agrego al carrito");
        } else {
            redirectAttributes.addFlashAttribute("libroNoAgregado", "Para agregar un libro al carrito tiene que ");
        }

        return new ModelAndView("redirect:/libro/{libroId}", modelo);
    }

    @RequestMapping("/quitar-libro-del-carrito/{id}")
    public ModelAndView quitarLibroDelCarrito(@PathVariable("id") Integer idLibro,
                                              RedirectAttributes redirectAttributes) {
        ModelMap modelo = new ModelMap();

        Integer usuarioId = (Integer) request.getSession().getAttribute("USUARIO_ID");

        servicioCarrito.quitarLibroDelCarrito(idLibro, usuarioId);

        return new ModelAndView("redirect:/carrito", modelo);
    }
}
