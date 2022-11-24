package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.Publicacion.RepositorioPublicacion;
import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.RepositorioLibro;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioLibroTest extends SpringTest {

    @Autowired
    RepositorioLibro repositorioLibro;

    @Test
    @Transactional
    @Rollback
    public void testBuscarLibroPorId() {
        Libro libro = new Libro();

        repositorioLibro.guardarLibro(libro);
        Libro libroEncontrado = repositorioLibro.buscarLibroPorId(libro.getId());

        assertThat(libroEncontrado.getId()).isNotNull();

    }

    @Test
    @Transactional
    @Rollback
    public void testDevolverTodosLosLibros() {

        List<Libro> libros = repositorioLibro.devolverTodosLosLibros();

        assertThat(libros).isNotNull();

    }

    @Test
    @Transactional
    @Rollback
    public void testObtenerListaDeLibrosEnNovedad() {

        List<Libro> novedades = repositorioLibro.obtenerListaDeLibrosEnNovedad();
        assertThat(novedades).isNotNull();

    }

    @Test
    @Transactional
    @Rollback
    public void crearLibro(){
        Libro libro = new Libro();
        session().save(libro);
        assertThat(libro.getId()).isNotNull();
    }
}