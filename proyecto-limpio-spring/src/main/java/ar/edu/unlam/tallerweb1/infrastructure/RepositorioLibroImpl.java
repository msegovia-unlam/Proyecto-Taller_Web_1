package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.Calificacion.Calificacion;
import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.libros.LibroComprado;
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
    public List<Libro> buscarLibroPorTituloALaVenta(String titulo) {
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
                .add(Restrictions.gt("cantidadEnStock", 0))
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
                .add(Restrictions.eq("aLaVenta", true))
                .list();
    }

    @Override
    public List<Libro> buscarRelacionadosPorGenero(String genero) {
        return this.sessionFactory.getCurrentSession().createCriteria(Libro.class)
                .add(Restrictions.like("genero", genero, MatchMode.ANYWHERE))
                .list();
    }

    @Override
    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        return this.sessionFactory.getCurrentSession().createCriteria(Libro.class)
                .add(Restrictions.like("titulo", titulo, MatchMode.ANYWHERE))
                .list();
    }

    @Override
    public List<Libro> buscarLibrosPorAutor(String autor) {
        return this.sessionFactory.getCurrentSession().createCriteria(Libro.class)
                .add(Restrictions.like("autor", autor, MatchMode.ANYWHERE))
                .list();
    }

    @Override
    public void asignarLibroComprado(Libro libro, Usuario usuario) {
        LibroComprado libroComprado = new LibroComprado();
        libroComprado.setUsuario(usuario);
        libroComprado.setLibro(libro);
        this.sessionFactory.getCurrentSession().save(libroComprado);
    }

    @Override
    public List<Libro> obtenerLibrosComprados(Usuario usuario) {
        List<Libro> listaDeLibros = this.sessionFactory.getCurrentSession().createCriteria(Libro.class)
                .createAlias("libroComprados", "lc")
                .add(Restrictions.eq("lc.usuario", usuario))
                .list();
        return listaDeLibros;
    }

    @Override
    public void calificarLibro(Integer idLibro, Integer calificacionNumero, Integer idUsuario) {

        final Session session= this.sessionFactory.getCurrentSession();

        Libro libroACalificar = buscarLibroPorId(idLibro);

        Usuario usuarioCalifica = (Usuario) this.sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("id", idUsuario))
                .uniqueResult();

        Calificacion calificacion = new Calificacion();
        calificacion.setUsuario(usuarioCalifica);
        calificacion.setCalificacion(calificacionNumero);
        calificacion.setLibro(libroACalificar);

        sessionFactory.getCurrentSession().save(calificacion);

    }

    @Override
    public Calificacion buscarCalificacionPorLibroYUsuario(Integer idLibro, Integer idUsuario) {

        Libro libroABuscar = buscarLibroPorId(idLibro);

        Usuario usuarioABuscar = (Usuario) this.sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("id", idUsuario))
                .uniqueResult();

        Calificacion calificacion = (Calificacion) this.sessionFactory.getCurrentSession().createCriteria(Calificacion.class)
                .add(Restrictions.eq("usuario", usuarioABuscar))
                .add(Restrictions.eq("libro",libroABuscar ))
                .uniqueResult();

        return calificacion;

    }

    @Override
    public void actualizarCalificacion(Integer idLibro, Integer calificacion, Integer idUsuario) {

        Libro libroABuscar = buscarLibroPorId(idLibro);

        Usuario usuarioABuscar = (Usuario) this.sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("id", idUsuario))
                .uniqueResult();

        Calificacion calificacion1 = (Calificacion) this.sessionFactory.getCurrentSession().createCriteria(Calificacion.class)
                .add(Restrictions.eq("usuario", usuarioABuscar))
                .add(Restrictions.eq("libro",libroABuscar ))
                .uniqueResult();

        calificacion1.setCalificacion(calificacion);

        sessionFactory.getCurrentSession().update(calificacion1);

    }

    @Override
    public List<Calificacion> calificacionesLibro(Libro libroCalificado) {

        List<Calificacion> Calificaciones = sesion().createCriteria(Calificacion.class).add(Restrictions.eq("libro", libroCalificado)).list();

        return Calificaciones;
    }

    @Override
    public Integer obtenerUsuariosQueCalificarionUnLibro(Libro libro) {

        return sesion().createCriteria(Calificacion.class).add(Restrictions.eq("libro", libro)).list().size();

    }


}
