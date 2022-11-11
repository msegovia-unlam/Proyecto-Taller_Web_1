package ar.edu.unlam.tallerweb1.domain.Publicacion;
import ar.edu.unlam.tallerweb1.domain.Encuesta.Encuesta;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioPublicacionImpl implements  ServicioPublicacion{

    private RepositorioPublicacion repositorioPublicacion;
    private ServletContext servletContext;

    @Autowired
    public ServicioPublicacionImpl(RepositorioPublicacion repositorioPublicacion, ServletContext servletContext) {
        this.repositorioPublicacion=repositorioPublicacion;
        this.servletContext=servletContext;
    }
    @Override
    public void crearPublicacion(String publicacion, LocalDateTime fecha, Usuario usuario){
        repositorioPublicacion.agregarPublicacion(publicacion,fecha,usuario);
    }
    @Override
    public List <Publicacion> getPublicaciones(List <Usuario> usuariosSeguidos){
        List<Publicacion> publicaciones = new ArrayList<>();
        for (Usuario usuario: usuariosSeguidos) {
            publicaciones.addAll(repositorioPublicacion.getPublicaciones(usuario));
        }

       return publicaciones;
    }
    @Override
    public void crearPublicacionConEncuesta(String publicacion, LocalDateTime fecha, Usuario usuario, Encuesta encuesta){
        repositorioPublicacion.agregarPublicacionConEncuesta(publicacion,fecha,usuario, encuesta);
    }
    @Override
    public void votar(Usuario usuarioLogueado, Integer encuestaId, String opcionElegida){

        Encuesta encuesta = repositorioPublicacion.buscarEncuestaPorId(encuestaId);

        repositorioPublicacion.votar(usuarioLogueado,encuesta,opcionElegida);
    }
    @Override
    public Boolean verificarDobleVoto(Usuario usuarioLogueado, Integer encuestaId){

      Encuesta encuesta =  repositorioPublicacion.buscarEncuestaPorId(encuestaId);
       return repositorioPublicacion.verificarDobleVoto(usuarioLogueado, encuesta);
    }

}
