package ar.edu.unlam.tallerweb1.domain.libros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicioLibroImpl implements ServicioLibro {

    private RepositorioLibro repositorioLibro;

    @Autowired
    public ServicioLibroImpl (RepositorioLibro repositorioLibro) {
        this.repositorioLibro = repositorioLibro;
    }

    @Override
    public Integer guardarLibro(Libro libro) {
        repositorioLibro.guardarLibro(libro);
        return libro.getId();
    }

    @Override
    public Libro buscarLibroPorId(Integer id) {
        return repositorioLibro.buscarLibroPorId(id);
    }

    @Override
    public void borrarLibro(Libro libro) {
        repositorioLibro.borrarlibro(libro);
    }

    @Override
    public List<Libro> obtenerLibrosALaVenta() {
        List<Libro> listaDeLibros = repositorioLibro.obtenerListaDeLibros();
        // Con esto se filtran solo los libros que tengan en true la propiedad a la venta
        List<Libro> listaDeLibrosALaVenta = listaDeLibros.stream().filter((libro -> libro.getALaVenta())).collect(Collectors.toList());
        return listaDeLibrosALaVenta;
    }

    @Override
    public Integer modificarLibro(Libro libro) {
        return repositorioLibro.modificarLibro(libro);
    }
}
