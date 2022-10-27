package ar.edu.unlam.tallerweb1.infrastructure;
import ar.edu.unlam.tallerweb1.domain.Carrito.Carrito;
import ar.edu.unlam.tallerweb1.domain.Carrito.RepositorioCarrito;
import ar.edu.unlam.tallerweb1.domain.libros.Libro;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioCarritoImpl implements RepositorioCarrito{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCarritoImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session sesion(){
        return this.sessionFactory.getCurrentSession();
    }
    @Override
    public void agregarLibroAlCarrito(Usuario usuario, Libro libro){
        sesion().save(new Carrito(usuario,libro,1));
    }
    @Override
    public List <Libro> obtenerListaDeIdDeLibrosDelCarrito(Usuario usuario){
        List<Libro> libros = sesion().createCriteria(Libro.class)
                .createAlias("carrito", "carritoBuscado")
                .add(Restrictions.eq("carritoBuscado.usuario", usuario))
                .list();
        for (Libro libro : libros) {
            List<Carrito> carritosAAgregar = new ArrayList<>();
            List<Carrito> carritos = libro.getCarrito();
            for(Carrito carrito : carritos) {
                if(carrito.getUsuario().getId().equals(usuario.getId()))
                    carritosAAgregar.add(carrito);
            }
            libro.setCarrito(carritosAAgregar);
        }
        return libros;
    }
    @Override
    public void quitarLibroDelCarrito(Usuario usuario, Libro libro){
        List<Carrito> listaCarrito = sesion().createCriteria(Carrito.class)
                .add(Restrictions.eq("usuario", usuario))
                .add(Restrictions.eq("libro", libro))
                .list();

        for(Carrito carrito: listaCarrito){
            sesion().delete(carrito);
        }
    }

    @Override
    public void actualizarCantidad(Libro libro, Integer nuevaCantidad, Usuario usuario) {
        List<Carrito> carritos = this.sessionFactory.getCurrentSession().createCriteria(Carrito.class)
                .add(Restrictions.eq("usuario", usuario))
                .add(Restrictions.eq("libro", libro))
                .list();
        List<Libro> libros = new ArrayList<>();
        for (Carrito carrito : carritos) {
            libros.add(carrito.getLibro());
        }
        Integer cantidadAAgregar = nuevaCantidad - libros.size();
        if(cantidadAAgregar < 0) {
            cantidadAAgregar *= -1;
            for (int i = 0; i < cantidadAAgregar; i++) {
                this.sessionFactory.getCurrentSession().delete(carritos.get(0));
            }
        } else {
            for (int i = 0; i < cantidadAAgregar; i++) {
                Carrito carrito = new Carrito();
                carrito.setLibro(libro);
                carrito.setUsuario(usuario);
                this.sessionFactory.getCurrentSession().save(carrito);
            }
        }
    }

}
