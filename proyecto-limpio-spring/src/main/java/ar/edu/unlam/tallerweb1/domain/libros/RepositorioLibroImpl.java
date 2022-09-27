package ar.edu.unlam.tallerweb1.domain.libros;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioLibroImpl implements RepositorioLibro {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioLibroImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long guardarLibro(Libro libro) {
        final Session session= this.sessionFactory.getCurrentSession();
        session.save(libro);
        return libro.getId();
    }

    @Override
    public List<Libro> obtenerListaDeLibros() {
        return this.sessionFactory
                .getCurrentSession()
                .createCriteria(Libro.class)
                .list();
    }
}
