package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.Follows.Follows;
import ar.edu.unlam.tallerweb1.domain.Follows.ServicioFollows;
import ar.edu.unlam.tallerweb1.domain.Publicacion.Publicacion;
import ar.edu.unlam.tallerweb1.domain.Publicacion.ServicioPublicacion;
import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
import ar.edu.unlam.tallerweb1.domain.usuarios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ar.edu.unlam.tallerweb1.domain.Votacion.VotacionesTotales;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/red-social/")
public class ControladorRedSocial {

    private ServicioLibro servicioLibro;
    private ServicioUsuario servicioUsuario;
    private HttpServletRequest request;
    private ServicioLogin servicioLogin;
    private ServicioRegistro servicioRegistro;
    private ServicioPublicacion servicioPublicacion;
    private ServicioFollows servicioFollows;

    @Autowired
    public ControladorRedSocial(ServicioLibro servicioLibro, ServicioUsuario servicioUsuario,
                                HttpServletRequest request, ServicioLogin servicioLogin,
                                ServicioRegistro servicioRegistro, ServicioPublicacion servicioPublicacion,
                                ServicioFollows servicioFollows) {
        this.servicioLibro = servicioLibro;
        this.servicioUsuario = servicioUsuario;
        this.request = request;
        this.servicioLogin = servicioLogin;
        this.servicioRegistro = servicioRegistro;
        this.servicioPublicacion = servicioPublicacion;
        this.servicioFollows = servicioFollows;
    }

    @RequestMapping("/")
    public ModelAndView irARedSocial(@ModelAttribute("datosLogin") DatosLogin datosLogin,
                                     @RequestParam(value = "idPublicacion", required = false) String idPublicacion,
                                     RedirectAttributes redirectAttributes) {
        ModelMap modelo = new ModelMap();
        String vista;

        if (request.getSession().getAttribute("ROL") != null) {
            Integer usuarioId = (Integer) request.getSession().getAttribute("USUARIO_ID");
            Usuario usuarioBuscado = servicioLogin.buscarUsuarioPorId(usuarioId);

            List<Usuario> usuariosSeguidos = servicioFollows.getUsuariosSeguidos(usuarioId);
            if(null != idPublicacion) {

                List<VotacionesTotales> Lista;
                Lista = servicioPublicacion.obtenerVotosTotales( Integer.valueOf(idPublicacion));
                Map<VotacionesTotales, Integer> mapaPorcentaje = new HashMap<>();

                Integer votosTotales = 0;


                for (VotacionesTotales voto : Lista ) {
                    votosTotales += voto.getCantidadVotos();
                }

                for (VotacionesTotales voto : Lista) {
                    Integer porcentaje = 0;

                    porcentaje = voto.getCantidadVotos()*100/votosTotales;
                    mapaPorcentaje.put(voto, porcentaje);
                }


                modelo.addAttribute("votaciones", Lista);
                modelo.addAttribute("porcentaje", mapaPorcentaje);

            }
            List<Publicacion> publicaciones = servicioPublicacion.getPublicaciones(usuariosSeguidos);

            modelo.addAttribute("publicaciones", publicaciones);
            modelo.addAttribute("publicacion", new Publicacion());
            vista = "red-social/home";
        } else {
            vista = "red-social/login";
        }
        return new ModelAndView(vista, modelo);
    }

    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        String vista;
        if (request.getSession().getAttribute("ROL") == null) {
            // invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
            // hace una llamada a otro action a traves de la URL correspondiente a esta
            Usuario usuarioBuscado = servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());
            if (usuarioBuscado != null) {
                request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
                request.getSession().setAttribute("USUARIO_ID", usuarioBuscado.getId());
                vista = "redirect:/red-social/";
            } else {
                // si el usuario no existe agrega un mensaje de error en el modelo.
                model.put("error", "Usuario o clave incorrecta");
                vista = "red-social/login";
            }
        } else {
            vista = "redirect:/";
        }
        return new ModelAndView(vista, model);
    }

    @RequestMapping(path = "/registro", method = RequestMethod.GET)
    ModelAndView irAlRegistro() {
        ModelMap modelo = new ModelMap();
        String vista;
        if (request.getSession().getAttribute("ROL") == null) {
            modelo.put("datosRegistro", new DatosRegistro());
            vista = "red-social/registro";
        } else {
            vista = "redirect:/red-social/";
        }
        return new ModelAndView(vista, modelo);
    }

    @RequestMapping(path = "/validar-registro", method = RequestMethod.POST)
    public ModelAndView validarRegistro(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro) {
        ModelMap model = new ModelMap();
        String vista;
        if (request.getSession().getAttribute("ROL") == null) {
            Usuario usuarioBuscado = servicioRegistro.consultarUsuario(datosRegistro.getEmail(), datosRegistro.getUsuarioName());
            if (usuarioBuscado != null) {
                model.put("error", "El usuario ya existe");
            } else {
                usuarioBuscado = new Usuario();
                usuarioBuscado.setEmail(datosRegistro.getEmail());
                usuarioBuscado.setNombre(datosRegistro.getUsuarioName());
                usuarioBuscado.setPassword(datosRegistro.getPassword());
                usuarioBuscado.setRol(Rol.NO_ADMIN);
                servicioRegistro.almacenarUsuario(usuarioBuscado);
                model.put("exitoso", "Se ha registrado correctamente");
            }
            vista = "red-social/registro";
        } else {
            vista = "redirect:/red-social/";
        }
        return new ModelAndView(vista, model);
    }

    @RequestMapping("/busqueda")
    public ModelAndView busqueda(@RequestParam(value = "busquedaLibro", defaultValue = "") String busquedaLibro,
                                 @RequestParam(value = "busquedaUsuario", defaultValue = "") String busquedaPersona) {
        ModelMap modelo = new ModelMap();

        if (!busquedaLibro.isEmpty() && busquedaPersona.isEmpty()) {
            if (busquedaLibro.equals(" ")) {
                modelo.addAttribute("mensajeError", "La busqueda no puede ir vacia");
                return new ModelAndView("red-social/busqueda-global", modelo);
            } else {
                Set<Libro> libros = new HashSet<>(servicioLibro.buscarLibrosPorTituloYAutor(busquedaLibro));
                Map<Integer, Integer> mapIdLibroYPromedio = new HashMap<>();
                Map<Integer, Integer> mapIdLibroYCantidadDeUsuariosCalificaron = new HashMap<>();

                for (Libro libro : libros) {
                    Integer promedioLibro = servicioLibro.obtenerPromedioGlobal(libro.getId());
                    mapIdLibroYPromedio.put(libro.getId(), promedioLibro);

                    Integer cantidadDeUsuariosCalificaron = servicioLibro.obtenerUsuariosQueCalificarionUnLibro(libro.getId());
                    mapIdLibroYCantidadDeUsuariosCalificaron.put(libro.getId(), cantidadDeUsuariosCalificaron);
                }

                modelo.addAttribute("libroIdYPromedio", mapIdLibroYPromedio);
                modelo.addAttribute("libroIdYCantidadDeUsuariosCalificaron", mapIdLibroYCantidadDeUsuariosCalificaron);
                modelo.addAttribute("libros", libros);
            }
        } else if (!busquedaPersona.isEmpty() && busquedaLibro.isEmpty()) {
            if (busquedaPersona.equals(" ")) {
                modelo.addAttribute("mensajeError", "La busqueda no puede ir vacia");
                return new ModelAndView("red-social/busqueda-global", modelo);
            } else {
                Set<Usuario> usuarios = new HashSet<>(servicioUsuario.obtenerUsuariosPorNombre(busquedaPersona));
                modelo.addAttribute("usuarios", usuarios);
            }
        } else {
            modelo.addAttribute("mensajeError", "La busqueda no puede ir vacia");
            return new ModelAndView("red-social/busqueda-global", modelo);
        }

        return new ModelAndView("red-social/busqueda-global", modelo);
    }

    @RequestMapping("/perfil")
    public ModelAndView irAPerfil(){

        ModelMap modelo = new ModelMap();

        Integer usuarioId = (Integer) request.getSession().getAttribute("USUARIO_ID");

        if(usuarioId == null) {
            return new ModelAndView("redirect:/red-social/", modelo);
        }

        Usuario usuarioBuscado = servicioLogin.buscarUsuarioPorId(usuarioId);
        modelo.addAttribute("usuario", usuarioBuscado);

        return new ModelAndView("red-social/perfil",modelo);
    }

    @RequestMapping(value = "/agregar-publicacion", method = RequestMethod.POST)
    public ModelAndView agregarPublicacion(@ModelAttribute("publicacion") Publicacion datosPublicacion) {
        ModelMap modelo = new ModelMap();
        LocalDateTime fecha = LocalDateTime.now();

        Integer usuarioId = (Integer) request.getSession().getAttribute("USUARIO_ID");
        Usuario usuario = servicioLogin.buscarUsuarioPorId(usuarioId);


        if(datosPublicacion.getEncuesta().getOpcion1()!=null){


            servicioPublicacion.crearPublicacionConEncuesta(datosPublicacion.getPublicacion(), fecha,
                    usuario, datosPublicacion.getEncuesta());
        }else{
            servicioPublicacion.crearPublicacion(datosPublicacion.getPublicacion(), fecha,
                    usuario);
        }


        return new ModelAndView("redirect:/red-social/", modelo);

    }

    @RequestMapping("/my-books")
    public ModelAndView irAMyBooks() {
        ModelMap modelo = new ModelMap();
        String vista;
        if (request.getSession().getAttribute("ROL") != null) {
            Integer idUsuario = (Integer) request.getSession().getAttribute("USUARIO_ID");
            Usuario usuarioBuscado = servicioLogin.buscarUsuarioPorId(idUsuario);
            Set<Libro> librosComprados = new HashSet<>(servicioLibro.obtenerLibrosComprados(idUsuario));

            Map<Integer, Integer> mapIdLibroYPromedio = new HashMap<>();
            Map<Integer, Integer> mapIdLibroYCantidadDeUsuariosCalificaron = new HashMap<>();

            for (Libro libro : librosComprados) {
                Integer promedioLibro = servicioLibro.obtenerPromedioGlobal(libro.getId());
                mapIdLibroYPromedio.put(libro.getId(), promedioLibro);

                Integer cantidadDeUsuariosCalificaron = servicioLibro.obtenerUsuariosQueCalificarionUnLibro(libro.getId());
                mapIdLibroYCantidadDeUsuariosCalificaron.put(libro.getId(), cantidadDeUsuariosCalificaron);
            }

            modelo.addAttribute("libroIdYPromedio", mapIdLibroYPromedio);
            modelo.addAttribute("libroIdYCantidadDeUsuariosCalificaron", mapIdLibroYCantidadDeUsuariosCalificaron);
            modelo.addAttribute("librosComprados", librosComprados);
            modelo.put("usuario",usuarioBuscado);
            vista = "red-social/my-books";
        } else {
            vista = "redirect:/red-social/";
        }
        return new ModelAndView(vista, modelo);
    }



    @RequestMapping(value = "/modificar-perfil/{id}")
    public ModelAndView modificarPerfil(@PathVariable("id") Integer id) {

        ModelMap modelo = new ModelMap();
        String vista;

        Usuario usuarioBuscado = servicioLogin.buscarUsuarioPorId(id);
        modelo.put("usuario", usuarioBuscado);

        vista = "red-social/modificar-perfil";

        return new ModelAndView(vista, modelo);
    }

    @RequestMapping(path = "/actualizarPerfil", method = RequestMethod.POST)
    public ModelAndView actualizarPerfil(@ModelAttribute("usuario") Usuario usuarioAActualizar, MultipartFile file) {
        ModelMap modelo = new ModelMap();
        String vista;

        if(!file.isEmpty()){
            servicioUsuario.actualizarUsuario(usuarioAActualizar, file);
        }else{
            servicioUsuario.actualizarUsuario(usuarioAActualizar);
        }


        modelo.put("usuario", usuarioAActualizar);
        vista = "red-social/perfil";

        return new ModelAndView(vista, modelo);

    }

    @RequestMapping(path = "/usuario/{id}", method = RequestMethod.GET)
    public ModelAndView verPerfilDeUsuario(@PathVariable("id") Integer usuarioId) {
        ModelMap modelo = new ModelMap();
        Usuario usuario = servicioLogin.buscarUsuarioPorId(usuarioId);
        Usuario usuarioEnLaSesion;

        if(usuario == null)
            return new ModelAndView("redirect:/", modelo);

        if(request.getSession().getAttribute("USUARIO_ID") != null) {
            Integer usuarioEnLaSesionId = (int) request.getSession().getAttribute("USUARIO_ID");
            usuarioEnLaSesion = servicioLogin.buscarUsuarioPorId(usuarioEnLaSesionId);

            if(usuarioEnLaSesion.getId().equals(usuarioId))
                modelo.addAttribute("msjMismoUsuario", "verdadero");
        } else {
            return new ModelAndView("red-social/home", modelo);
        }

        List<Usuario> usuariosSeguidos = servicioFollows.getUsuariosSeguidos(usuarioEnLaSesion.getId());

        usuariosSeguidos.forEach(usuario1 -> {
            if (usuario1.getId().equals(usuarioId))
                modelo.addAttribute("usuarioYaSiendoSeguido", "verdadero");
        });

        modelo.addAttribute("usuario", usuario);

        return new ModelAndView("red-social/perfil-otro-usuario", modelo);
    }

    @RequestMapping(path = "/seguir/{usuarioASeguirId}", method = RequestMethod.POST)
    public ModelAndView seguirUsuario(@PathVariable("usuarioASeguirId") Integer usuarioASeguirId, RedirectAttributes redirectAttributes) {
        ModelMap modelo = new ModelMap();

        if (request.getSession().getAttribute("ROL") != null) {
            Integer idUsuarioSeguidor = (Integer) request.getSession().getAttribute("USUARIO_ID");
            servicioFollows.crearSeguimiento(idUsuarioSeguidor, usuarioASeguirId);
            redirectAttributes.addFlashAttribute("msjFollow", "??Ahora estas siguiendo a");
            return new ModelAndView("redirect:/red-social/usuario/" + usuarioASeguirId, modelo);
        } else {
            return new ModelAndView("red-social/my-books", modelo);
        }
    }

    @RequestMapping(path = "/libro/{id}")
    public ModelAndView verDetallesLibro(@PathVariable("id") Integer libroId) {
        ModelMap modelo = new ModelMap();
        Libro libro = servicioLibro.buscarLibroPorId(libroId);

        if(libro == null)
            return new ModelAndView("redirect:/red-social/", modelo);

        Integer promedioGeneral = servicioLibro.obtenerPromedioGlobal(libroId);
        Integer cantUsuariosCalifican = servicioLibro.obtenerUsuariosQueCalificarionUnLibro(libroId);

        modelo.addAttribute("libro", libro);
        modelo.addAttribute("calificacion", promedioGeneral);
        modelo.addAttribute("usuariosCalificacion", cantUsuariosCalifican);
        return new ModelAndView("red-social/libro", modelo);
    }

    @RequestMapping(path = "/cerrar-sesion", method = RequestMethod.GET)
    public ModelAndView cerrarSesion() {
        ModelMap model = new ModelMap();
        if(request.getSession().getAttribute("ROL") != null) {
            request.getSession().removeAttribute("ROL");
            request.getSession().removeAttribute("USUARIO_ID");
        }
        return new ModelAndView("redirect:/red-social/", model);
    }

    @RequestMapping(path = "/votar", method = RequestMethod.POST)
        public ModelAndView votar(@RequestParam(value = "encuesta", defaultValue = "null") String opcionElegida,
                                  @RequestParam("encuestaId") Integer encuestaId ){

            ModelMap model = new ModelMap();

            if (opcionElegida.equals("null")) {
                return new ModelAndView("redirect:/red-social/", model);
            }


            Integer idUsuario = (Integer) request.getSession().getAttribute("USUARIO_ID");
            Usuario usuarioLogueado = servicioLogin.buscarUsuarioPorId(idUsuario);

            Boolean votoDoble=servicioPublicacion.verificarDobleVoto(usuarioLogueado, encuestaId);

            if(!votoDoble){
            servicioPublicacion.votar(usuarioLogueado, encuestaId, opcionElegida);
            }

            List<VotacionesTotales> Lista;
            Lista = servicioPublicacion.obtenerVotosTotales(encuestaId);
            model.addAttribute("votaciones", Lista);

            return new ModelAndView("redirect:/red-social/?idPublicacion=" + encuestaId , model);
        }

    @ModelAttribute("usuarioEnLaSesion")
    public Usuario obtenerUsuarioEnLaSesion() {
        if(request.getSession().getAttribute("ROL") != null) {
            Integer usuarioId = (int) request.getSession().getAttribute("USUARIO_ID");
            Usuario usuarioEnLaSesion = servicioLogin.buscarUsuarioPorId(usuarioId);
            return usuarioEnLaSesion;
        }
        return null;
    }

}
