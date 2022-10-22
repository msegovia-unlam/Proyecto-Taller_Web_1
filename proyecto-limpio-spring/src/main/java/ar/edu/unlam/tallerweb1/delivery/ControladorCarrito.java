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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
            boolean seAgrego = servicioCarrito.agregarLibroAlCarrito(usuario, libro);
            if (seAgrego)
                redirectAttributes.addFlashAttribute("libroAgregado", "El libro se agrego al carrito");
            else
                redirectAttributes.addFlashAttribute("libroNoAgregado", "No hay stock");
        } else {
            redirectAttributes.addFlashAttribute("libroNoAgregado", "Para agregar un libro al carrito tiene que estar logueado");
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

    @RequestMapping(value = "/actualizar-cantidad-del-carrito/{id}", method = RequestMethod.POST)
    public ModelAndView actualizarCantidadDeUnLibroEnCarrito(@PathVariable("id") Integer idLibro, @RequestParam(value = "nuevaCantidad", defaultValue = "-1") Integer nuevaCantidad, RedirectAttributes redirectAttributes) {
        ModelMap modelo = new ModelMap();
        String vista;

        Libro libro = servicioLibro.buscarLibroPorId(idLibro);
        if (libro == null || nuevaCantidad == -1)
            return new ModelAndView("redirect:/");
        Integer usuarioId = (Integer) request.getSession().getAttribute("USUARIO_ID");
        boolean seActualizo = servicioCarrito.actualizarCantidad(libro, nuevaCantidad, usuarioId);
        if (!seActualizo) {
            redirectAttributes.addFlashAttribute("errorAgregar", "No hay suficiente stock");
        }
        vista = "redirect:/carrito";

        return new ModelAndView(vista, modelo);
    }

    @RequestMapping(path = "/carrito-compra", method = RequestMethod.POST)
    public ModelAndView realizarCompraCarrito(@RequestParam("librosId") String libros, RedirectAttributes redirectAttributes) {

        ModelMap modelo = new ModelMap();
        boolean verficacionStock = true;

        if(libros.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorAgregar", "No se puede completar la compra. El carrito esta vacio");
            return new ModelAndView("redirect:/carrito/", modelo);
        }

        libros = libros.substring(0, libros.length() - 1);
        List<String> librosYCantidad = Arrays.asList(libros.split(","));
        Map<Integer, Integer> mapLibroYCantidad = new HashMap<>();
        for (String libroYCantidad : librosYCantidad) {
            String[] librYCant = libroYCantidad.split(":");

            Integer idLibro = Integer.parseInt(librYCant[0]);
            Integer cantidadDelLibro = Integer.parseInt(librYCant[1]);

            verficacionStock = servicioLibro.verificarStock(idLibro, cantidadDelLibro);

            mapLibroYCantidad.put(idLibro, cantidadDelLibro);
        }

        if (!verficacionStock) {
            redirectAttributes.addFlashAttribute("errorAgregar", "Hubo un error en la compra por falta de stock");
            new ModelAndView("redirect:/carrito/", modelo);
        } else {
            mapLibroYCantidad.forEach((idLibro, cantidadDelLibro) -> {
                for (int i = 0;i < cantidadDelLibro;i++) {
                    servicioLibro.comprarLibro(idLibro);
                }
                Integer usuarioId = (Integer) request.getSession().getAttribute("USUARIO_ID");
                servicioCarrito.quitarLibroDelCarrito(idLibro, usuarioId);
            });

            redirectAttributes.addFlashAttribute("compraExitosa", "Se realizo la compra con exito");
        }

        return new ModelAndView("redirect:/carrito/", modelo);
    }

}
