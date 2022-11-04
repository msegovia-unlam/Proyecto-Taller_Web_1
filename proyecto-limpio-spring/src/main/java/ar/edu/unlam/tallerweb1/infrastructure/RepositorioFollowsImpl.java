package ar.edu.unlam.tallerweb1.infrastructure;
import ar.edu.unlam.tallerweb1.domain.Follows.Follows;
import ar.edu.unlam.tallerweb1.domain.Follows.RepositorioFollows;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        List<Follows> follows = sesion().createCriteria(Follows.class)
                .createAlias("usuarioSeguidor", "usuarioSeguidor")
                .add(Restrictions.eq("usuarioSeguidor", usuario))
                .list();

        List<Usuario> usuarios = new ArrayList<>();
        for (Follows follows1: follows) {
            usuarios.add(follows1.getUsuarioSeguido());
        }

        return usuarios;
    }

    @Override
    public void crearSeguimiento(Usuario usuarioSeguidor, Usuario usuarioASeguir) {
        Follows follows = new Follows();
        follows.setUsuarioSeguidor(usuarioSeguidor);
        follows.setUsuarioSeguido(usuarioASeguir);
        sesion().save(follows);
    }

}
