package ar.edu.unlam.tallerweb1.domain.Publicacion;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ServicioPublicacion{

    void crearPublicacion(String publicacion, LocalDateTime fecha, Usuario usuario);
    List<Publicacion> getPublicaciones(List<Usuario> usuariosSeguidos);

}
