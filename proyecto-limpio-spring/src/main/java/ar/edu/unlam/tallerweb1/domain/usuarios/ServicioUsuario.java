package ar.edu.unlam.tallerweb1.domain.usuarios;

import java.util.List;

public interface ServicioUsuario {
    List<Usuario> obtenerUsuariosPorNombre(String busquedaPersona);
}
