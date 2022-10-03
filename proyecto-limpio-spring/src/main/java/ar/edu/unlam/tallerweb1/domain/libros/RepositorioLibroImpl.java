package ar.edu.unlam.tallerweb1.domain.libros;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletContext;
import java.util.List;

@Repository
public class RepositorioLibroImpl implements RepositorioLibro {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioLibroImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Libro guardarLibro(Libro libro) {
        final Session session= this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(libro);
        return libro;
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
    public void actualizarLibro(Libro libroAActualizar){

        sessionFactory.getCurrentSession().update(libroAActualizar);
    }

    @Override
    public List<Libro> devolverTodosLosLibros() {
        return this.sessionFactory
                .getCurrentSession()
                .createCriteria(Libro.class)
                .list();
    }

    @Override
    public List<Libro> buscarLibroPorTitulo(String titulo) {
        return this.sessionFactory.getCurrentSession().createCriteria(Libro.class)
                .add(Restrictions.like("titulo",titulo, MatchMode.ANYWHERE))
                .list();
    }

    @Override
    public void cambiarEstadoDeVentaDelLibro(Integer id) {
       Libro libro = (Libro) this.sessionFactory.getCurrentSession().createCriteria(Libro.class)
                .add(Restrictions.eq("id",id))
               .uniqueResult();
       if(libro.getALaVenta()){
           libro.setALaVenta(false);
       }else{
           libro.setALaVenta(true);
       }
    }
}
