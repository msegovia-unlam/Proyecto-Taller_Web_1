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
    public List <Integer> obtenerListaDeIdDeLibrosDelCarrito(Usuario usuario){
        return  sesion().createCriteria(Libro.class)
                .createAlias("carrito", "carritoBuscado")
                .add(Restrictions.eq("carritoBuscado.usuario", usuario))
                .list();
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

//
//    @Override
//    public void quitarLibroDelCarrito(Integer id, Usuario usuario) {
//        Libro libroABorrar = (Libro) sesion().createCriteria(Libro.class)
//                .createAlias("carrito", "carritoBuscado")
//                .add(Restrictions.eq("carritoBuscado.usuario", usuario))
//                .add(Restrictions.eq("id", id))
//                .uniqueResult();
//
//        sesion().delete(libroABorrar);
//    }

}
