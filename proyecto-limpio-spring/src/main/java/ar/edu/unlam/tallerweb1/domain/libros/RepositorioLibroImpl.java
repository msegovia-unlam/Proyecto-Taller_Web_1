package ar.edu.unlam.tallerweb1.domain.libros;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
    public Integer guardarLibro(Libro libro) {
        final Session session= this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(libro);
        return libro.getId();
    }

    @Override
    public List<Libro> obtenerListaDeLibros() {
        return this.sessionFactory
                .getCurrentSession()
                .createCriteria(Libro.class)
                .list();
    }

    @Override
    public Libro buscarLibroPorId(Integer id) {
        return (Libro) this.sessionFactory.getCurrentSession().createCriteria(Libro.class)
                .add(Restrictions.eq("id",id))
                .uniqueResult();
    }

    @Override
    public void borrarlibro(Libro libro){
        this.sessionFactory.getCurrentSession().delete(libro);
    }

    @Override
    public Integer modificarLibro(Libro libro) {
        this.sessionFactory.getCurrentSession().update(libro);
        return libro.getId();
    }
}
