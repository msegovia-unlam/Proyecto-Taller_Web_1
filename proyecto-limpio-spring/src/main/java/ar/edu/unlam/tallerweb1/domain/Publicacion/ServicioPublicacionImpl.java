package ar.edu.unlam.tallerweb1.domain.Publicacion;
import ar.edu.unlam.tallerweb1.domain.libros.RepositorioLibro;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.time.LocalDate;
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

}
