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
    public Long guardarLibro(Libro libro) {
        repositorioLibro.guardarLibro(libro);
        return libro.getId();
    }

    @Override
    public List<Libro> obtenerLibrosALaVenta() {
        List<Libro> listaDeLibros = repositorioLibro.obtenerListaDeLibros();
        List<Libro> listaDeLibrosALaVenta = listaDeLibros.stream().filter((libro -> libro.getaLaVenta())).collect(Collectors.toList());
        return listaDeLibrosALaVenta;
    }
}
