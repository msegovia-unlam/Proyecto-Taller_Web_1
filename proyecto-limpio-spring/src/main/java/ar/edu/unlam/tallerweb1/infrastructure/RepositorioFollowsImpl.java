package ar.edu.unlam.tallerweb1.infrastructure;
import ar.edu.unlam.tallerweb1.domain.Follows.Follows;
import ar.edu.unlam.tallerweb1.domain.Follows.RepositorioFollows;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioFollowsImpl implements RepositorioFollows{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioFollowsImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session sesion(){
        return this.sessionFactory.getCurrentSession();
    }
    @Override
    public List <Usuario> getUsuariosSeguidos(Usuario usuario){
        return sesion().createCriteria(Usuario.class)
                .createAlias("usuarioSeguidor","usuarioSeguidor")
                .add(Restrictions.eq("usuarioSeguidor.usuarioSeguido", usuario))
                .list();
    }

}
