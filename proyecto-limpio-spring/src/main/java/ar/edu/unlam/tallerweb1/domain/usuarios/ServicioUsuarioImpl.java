package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.libros.ImagenLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario{

    private RepositorioUsuario repositorioUsuario;
    private ServletContext servletContext;

    @Autowired
    public ServicioUsuarioImpl (RepositorioUsuario repositorioUsuario, ServletContext servletContext) {
        this.repositorioUsuario = repositorioUsuario;
        this.servletContext = servletContext;
    }

    @Override
    public List<Usuario> obtenerUsuariosPorNombre(String nombre) {
        return repositorioUsuario.buscarUsuariosPorNombre(nombre);
    }

    @Override
    public void actualizarUsuario(Usuario usuarioAActualizar) {
        repositorioUsuario.actualizarUsuario(usuarioAActualizar);
    }

    @Override
    public void actualizarUsuario(Usuario usuarioAActualizar, MultipartFile file) {

        ImagenLibro imagenUsuario = new ImagenLibro();
        usuarioAActualizar.setImagen(imagenUsuario);

        usuarioAActualizar = repositorioUsuario.actualizarUsuario(usuarioAActualizar);

        try {
            Path carpetaImages = Path.of(servletContext.getRealPath("/") + "images");
            if (!Files.exists(carpetaImages))
                Files.createDirectory(carpetaImages);
            String nombreDeLaImagen = carpetaImages + "\\" + usuarioAActualizar.getImagen().getId() + ".jpg";
            file.transferTo(new File(nombreDeLaImagen));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
