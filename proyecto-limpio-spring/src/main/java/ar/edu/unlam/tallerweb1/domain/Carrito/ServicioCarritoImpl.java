package ar.edu.unlam.tallerweb1.domain.Carrito;
import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.RepositorioLibro;
import ar.edu.unlam.tallerweb1.domain.libros.ServicioLibro;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.util.List;

@Service
@Transactional
public class ServicioCarritoImpl implements  ServicioCarrito{

    RepositorioCarrito repositorioCarrito;
    RepositorioUsuario repositorioUsuario;
    ServicioLogin servicioLogin;
    ServicioLibro servicioLibro;

    @Autowired
    public ServicioCarritoImpl(RepositorioCarrito repositorioCarrito, RepositorioUsuario repositorioUsuario,
                               ServicioLogin servicioLogin, ServicioLibro servicioLibro) {
        this.repositorioCarrito=repositorioCarrito;
        this.repositorioUsuario=repositorioUsuario;
        this.servicioLogin=servicioLogin;
        this.servicioLibro= servicioLibro;
    }

    @Override
    public void agregarLibroAlCarrito(Usuario usuario, Libro libro){

        repositorioCarrito.agregarLibroAlCarrito(usuario, libro);

    }
    @Override
    public void quitarLibroDelCarrito(Integer idLibro, Integer idUsuario){

        Usuario usuario = servicioLogin.buscarUsuarioPorId(idUsuario);
        Libro libro = servicioLibro.buscarLibroPorId(idLibro);

        repositorioCarrito.quitarLibroDelCarrito(usuario, libro);

    }
    @Override
    public List <Integer> obtenerListaDeIdDeLibrosDelCarrito(Integer usuarioId){

        Usuario usuario = repositorioUsuario.buscarUsuarioPorId(usuarioId);

        return repositorioCarrito.obtenerListaDeIdDeLibrosDelCarrito(usuario);
    }

}
