package ar.edu.unlam.tallerweb1.domain.usuarios;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.util.List;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario buscarUsuario(String email, String password);
	void guardar(Usuario usuario);
    Usuario buscar(String email);
	Usuario busquedaUsuarioName(String userName);
	void modificar(Usuario usuario);
    Usuario buscarUsuarioPorId(Integer usuarioId);
	List<Usuario> buscarUsuariosPorNombre(String nombre);

    void actualizarUsuario(Usuario usuarioAActualizar);
}
