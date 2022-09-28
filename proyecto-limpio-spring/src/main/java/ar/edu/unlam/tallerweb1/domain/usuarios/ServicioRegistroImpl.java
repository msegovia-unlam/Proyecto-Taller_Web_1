package ar.edu.unlam.tallerweb1.domain.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioRegistro")
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro {

    private RepositorioUsuario servicioRegistroDao;

    @Autowired
    public ServicioRegistroImpl(RepositorioUsuario servicioRegistroDao) {
        this.servicioRegistroDao = servicioRegistroDao;
    }

    @Override
    public Usuario consultarUsuario(String email, String usuarioName) {
        Usuario usuarioABuscar;
        usuarioABuscar = servicioRegistroDao.busquedaUsuarioName(usuarioName);
        if(usuarioABuscar!=null){
            return usuarioABuscar;
        }
        else{
            usuarioABuscar= servicioRegistroDao.buscar(email);
            return usuarioABuscar;
        }
        //consulta usuario en la BD
    }

    @Override
    public void almacenarUsuario(Usuario usuario) {
        servicioRegistroDao.guardar(usuario);
        //guarda usuario en la BD
    }
}
