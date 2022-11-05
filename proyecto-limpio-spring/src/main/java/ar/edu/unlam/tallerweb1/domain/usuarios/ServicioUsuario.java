package ar.edu.unlam.tallerweb1.domain.usuarios;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServicioUsuario {
    List<Usuario> obtenerUsuariosPorNombre(String busquedaPersona);

    void actualizarUsuario(Usuario usuarioAActualizar);

    void actualizarUsuario(Usuario usuarioAActualizar, MultipartFile file);
}
