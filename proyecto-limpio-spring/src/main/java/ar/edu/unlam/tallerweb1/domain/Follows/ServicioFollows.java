package ar.edu.unlam.tallerweb1.domain.Follows;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.util.List;

public interface ServicioFollows{

    List <Usuario> getUsuariosSeguidos(Integer usuarioId);

}
