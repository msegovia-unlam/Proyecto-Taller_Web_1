package ar.edu.unlam.tallerweb1.infrastructure;
import ar.edu.unlam.tallerweb1.domain.Publicacion.Publicacion;
import ar.edu.unlam.tallerweb1.domain.Publicacion.RepositorioPublicacion;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioPublicacionImpl implements RepositorioPublicacion{


    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioPublicacionImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session sesion(){
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void agregarPublicacion(String publicacion, LocalDateTime fecha, Usuario usuario){

        sesion().save(new Publicacion(publicacion,fecha,0,usuario));


    }
    @Override
    public List <Publicacion> getPublicaciones(Usuario usuario){


        return sesion().createCriteria(Publicacion.class)
                .add(Restrictions.eq("usuario", usuario))
                .list();

    }

}
