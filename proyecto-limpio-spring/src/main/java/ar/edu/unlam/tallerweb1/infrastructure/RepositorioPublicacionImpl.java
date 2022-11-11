package ar.edu.unlam.tallerweb1.infrastructure;
import ar.edu.unlam.tallerweb1.domain.Encuesta.Encuesta;
import ar.edu.unlam.tallerweb1.domain.Publicacion.Publicacion;
import ar.edu.unlam.tallerweb1.domain.Publicacion.RepositorioPublicacion;
import ar.edu.unlam.tallerweb1.domain.Votacion.Voto;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

        sesion().save(new Publicacion(publicacion,fecha,usuario));


    }
    @Override
    public List <Publicacion> getPublicaciones(Usuario usuario){


        return sesion().createCriteria(Publicacion.class)
                .add(Restrictions.eq("usuario", usuario))
                .list();

    }
    @Override
    public void agregarPublicacionConEncuesta(String publicacion, LocalDateTime fecha, Usuario usuario, Encuesta encuesta){
        sesion().save(new Publicacion(publicacion,fecha,usuario, encuesta));
    }
    @Override
    public Encuesta buscarEncuestaPorId(Integer encuestaId){

        return (Encuesta) sesion().createCriteria(Encuesta.class)
                .add(Restrictions.eq("id", encuestaId))
                .uniqueResult();

    }
    @Override
    public void votar(Usuario usuarioLogueado, Encuesta encuesta, String opcionElegida){

        Voto voto = new Voto();

        voto.setEncuesta(encuesta);
        voto.setOpcionElegida(opcionElegida);
        voto.setUsuarioVotante(usuarioLogueado);
        sesion().save(voto);

    }
    @Override
    public Boolean verificarDobleVoto(Usuario usuarioLogueado, Encuesta encuesta){
        Voto voto = (Voto) sesion().createCriteria(Voto.class)
                .add(Restrictions.eq("usuarioVotante", usuarioLogueado))
                .add(Restrictions.eq("encuesta", encuesta))
                .uniqueResult();

        if(voto!=null){
            return true;
        }else {
            return false;
        }

    }

}
