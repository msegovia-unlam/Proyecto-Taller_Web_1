package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.RepositorioLibro;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class RepositorioLibroImpl implements RepositorioLibro{

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
    public List<Libro> obtenerListaDeLibrosALaVenta() {
        return  this.sessionFactory
                .getCurrentSession()
                .createCriteria(Libro.class)
                .add(Restrictions.eq("aLaVenta", true))
                .add(Restrictions.gt("cantidadEnStock", 0))
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
                .add(Restrictions.eq("aLaVenta", true))
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

    @Override
    public void cambiarEstadoDeNovedadDelLibro(Integer id) {
        Libro libro = (Libro) this.sessionFactory.getCurrentSession().createCriteria(Libro.class)
                .add(Restrictions.eq("id",id))
                .uniqueResult();
        if(libro.getNovedad()==null){
            libro.setNovedad(true);
        }else if(libro.getNovedad()){
            libro.setNovedad(false);
        }else{
            libro.setNovedad(true);
        }
    }



    @Override
    public List<Libro> obtenerListaDeLibrosEnNovedad() {

        return this.sessionFactory.getCurrentSession().createCriteria(Libro.class)
                .add(Restrictions.eq("novedad", true))
                .add(Restrictions.eq("aLaVenta", true))
                .list();
    }


    public Session sesion(){

        return this.sessionFactory.getCurrentSession();
    }
    @Override
    public boolean reducirStock(Integer idLibro) {
        Libro libro = this.buscarLibroPorId(idLibro);
        libro.setCantidadEnStock(libro.getCantidadEnStock() - 1);
        try {
            this.sessionFactory.getCurrentSession().update(libro);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Libro> buscarRelacionadosPorAutor(String autor) {
        return  this.sessionFactory
                .getCurrentSession()
                .createCriteria(Libro.class)
                .add(Restrictions.eq("aLaVenta", true))
                .add(Restrictions.eq("autor", autor))
                .list();

    }

    @Override
    public List<Libro> devolverLibroPorAutor(String autor) {
        return this.sessionFactory.getCurrentSession().createCriteria(Libro.class)
                .add(Restrictions.like("autor", autor, MatchMode.ANYWHERE))
                .list();
    }


}
