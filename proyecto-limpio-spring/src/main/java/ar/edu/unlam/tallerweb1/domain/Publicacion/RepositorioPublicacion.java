package ar.edu.unlam.tallerweb1.domain.Publicacion;
import ar.edu.unlam.tallerweb1.domain.Encuesta.Encuesta;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public interface RepositorioPublicacion{

    void agregarPublicacion(String publicacion, LocalDateTime fecha, Usuario usuario);
    List<Publicacion> getPublicaciones(Usuario usuario);
    void agregarPublicacionConEncuesta(String publicacion, LocalDateTime fecha, Usuario usuario, Encuesta encuesta);
    Encuesta buscarEncuestaPorId(Integer encuestaId);
    void votar(Usuario usuarioLogueado, Encuesta encuesta, String opcionElegida);
    Boolean verificarDobleVoto(Usuario usuarioLogueado, Encuesta encuesta);

}
