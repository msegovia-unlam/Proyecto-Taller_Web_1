package ar.edu.unlam.tallerweb1.domain.Follows;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.util.List;

public interface RepositorioFollows{

    List<Usuario> getUsuariosSeguidos(Usuario usuario);

}
