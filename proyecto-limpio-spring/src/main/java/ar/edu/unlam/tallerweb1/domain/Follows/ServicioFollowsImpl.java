package ar.edu.unlam.tallerweb1.domain.Follows;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioFollowsImpl implements ServicioFollows{

    private RepositorioFollows repositorioFollows;
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioFollowsImpl(RepositorioFollows repositorioFollows, RepositorioUsuario repositorioUsuario) {
       this.repositorioFollows=repositorioFollows;
       this.repositorioUsuario=repositorioUsuario;
    }
    @Override
    public List <Usuario> getUsuariosSeguidos(Integer usuarioId){

        Usuario usuario = repositorioUsuario.buscarUsuarioPorId(usuarioId);
        return repositorioFollows.getUsuariosSeguidos(usuario);
    }

}
