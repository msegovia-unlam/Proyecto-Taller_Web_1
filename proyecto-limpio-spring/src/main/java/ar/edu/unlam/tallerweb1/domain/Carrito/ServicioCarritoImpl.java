package ar.edu.unlam.tallerweb1.domain.Carrito;
import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioCarritoImpl implements  ServicioCarrito{

    RepositorioCarrito repositorioCarrito;
    RepositorioUsuario repositorioUsuario;
    ServicioLogin servicioLogin;
    ServicioLibro servicioLibro;

    @Autowired
    public ServicioCarritoImpl(RepositorioCarrito repositorioCarrito, RepositorioUsuario repositorioUsuario, ServicioLogin servicioLogin, ServicioLibro servicioLibro) {
        this.repositorioCarrito=repositorioCarrito;
        this.repositorioUsuario=repositorioUsuario;
        this.servicioLogin=servicioLogin;
        this.servicioLibro= servicioLibro;
    }

    @Override
    public boolean agregarLibroAlCarrito(Usuario usuario, Libro libro) {
        if (libro.getCantidadEnStock() > 0) {
            repositorioCarrito.agregarLibroAlCarrito(usuario, libro);
            return true;
        }
        else
            return false;
    }
    @Override
    public void quitarLibroDelCarrito(Integer idLibro, Integer idUsuario){

        Usuario usuario = servicioLogin.buscarUsuarioPorId(idUsuario);
        Libro libro = servicioLibro.buscarLibroPorId(idLibro);

        repositorioCarrito.quitarLibroDelCarrito(usuario, libro);

    }
    @Override
    public List <Libro> obtenerListaDeIdDeLibrosDelCarrito(Integer usuarioId){
        Usuario usuario = repositorioUsuario.buscarUsuarioPorId(usuarioId);
        return repositorioCarrito.obtenerListaDeIdDeLibrosDelCarrito(usuario);
    }

    @Override
    public boolean actualizarCantidad(Libro libro, Integer nuevaCantidad, Integer usuarioId) {
        if(libro.getCantidadEnStock() >= nuevaCantidad) {
            Usuario usuario = repositorioUsuario.buscarUsuarioPorId(usuarioId);
            repositorioCarrito.actualizarCantidad(libro, nuevaCantidad, usuario);
            return true;
        } else {
            return false;
        }
    }

}
