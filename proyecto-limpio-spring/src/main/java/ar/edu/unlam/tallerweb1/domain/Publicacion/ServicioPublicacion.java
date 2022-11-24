package ar.edu.unlam.tallerweb1.domain.Publicacion;
import ar.edu.unlam.tallerweb1.domain.Encuesta.Encuesta;
import ar.edu.unlam.tallerweb1.domain.Votacion.VotacionesTotales;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public interface ServicioPublicacion{

    void crearPublicacion(String publicacion, LocalDateTime fecha, Usuario usuario);
    List<Publicacion> getPublicaciones(List<Usuario> usuariosSeguidos);
    void crearPublicacionConEncuesta(String publicacion, LocalDateTime fecha, Usuario usuario, Encuesta encuesta);
    void votar(Usuario usuarioLogueado, Integer encuestaId, String opcionElegida);
    Boolean verificarDobleVoto(Usuario usuarioLogueado, Integer encuestaId);

    List<VotacionesTotales> obtenerVotosTotales(Integer encuestaId);
}
