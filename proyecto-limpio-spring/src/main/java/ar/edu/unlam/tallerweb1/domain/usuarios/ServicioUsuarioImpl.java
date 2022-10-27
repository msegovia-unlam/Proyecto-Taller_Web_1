package ar.edu.unlam.tallerweb1.domain.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario{

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioUsuarioImpl (RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public List<Usuario> obtenerUsuariosPorNombre(String nombre) {
        return repositorioUsuario.buscarUsuariosPorNombre(nombre);
    }

    @Override
    public void actualizarUsuario(Usuario usuarioAActualizar) {
        repositorioUsuario.actualizarUsuario(usuarioAActualizar);
    }
}
