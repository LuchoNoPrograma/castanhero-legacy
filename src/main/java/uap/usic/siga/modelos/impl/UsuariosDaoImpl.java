package uap.usic.siga.modelos.impl;

import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.Roles;
import uap.usic.siga.entidades.ScsArchivosAdjuntos;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.UsuariosDao;

/**
 *
 * @author fmbma
 */
@Repository("usuariossDao")
public class UsuariosDaoImpl implements UsuariosDao {

    @PersistenceContext
    private javax.persistence.EntityManager em;

    @Transactional(rollbackFor = {ServicioException.class})
    @Override
    public List<Roles> listarRolesGET() {
        String sql = "SELECT r FROM Roles r";
        Query q = em.createQuery(sql);
        return q.getResultList();

    }

    @Override
    public Usuarios buscarUsuariosPorIdUsuarioGET(Long id) {
        String sql = " Select u "
                + " from Usuarios u "
                + " Where u.id = :id ";
        Query q = em.createQuery(sql);
        q.setParameter("id", id);
        try {
            return (Usuarios) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Usuarios buscarPnlPersonalAdmnistrativoPorIdPersonaGET(Long idPersona) {
        String sql = " SELECT pnl "
                + " FROM PnlPersonalAdministrativos pnl LEFT JOIN pnl.personas pe "
                + " WHERE pe.idPersona = :idPersona "
                + " AND pnl.idEstado =:idEstado ";
        Query q = em.createQuery(sql);
        q.setParameter("idPersona", idPersona);
        try {
            return (Usuarios) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Usuarios> listarUsuariosRegistradosJPQL() {
        String sql = "SELECT u "
                + " FROM Usuarios u LEFT JOIN u.personas p "
                + " WHERE u.idEstado = 'A' "
                + " AND p.idEstado = 'A' " ;
        Query q = em.createQuery(sql);
        return q.getResultList();

    }

    @Override
    public void registrarUsuario(Usuarios usuarios){
        em.persist(usuarios);
    }

    @Override
    public void modificarUsuariosSET(Usuarios usuarios) {
        em.merge(usuarios);
    }

    @Override
    public void eliminarUsuariosSET(Usuarios usuarios) {
        em.merge(usuarios);
    }

	@Override
	public Usuarios buscarUsuariosPorNombreUsuarioGET(String name) {
		System.out.print("nombre"+name);
		String sql = " SELECT u FROM Usuarios u "
                + " WHERE u.name =:name "
                + " AND u.idEstado = 'A' " ;
        Query q = em.createQuery(sql);
        q.setParameter("name", name);
        try {
            return (Usuarios) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

	}

	@Override
	public Usuarios buscarUsuariosGET(String name) {
		 String sql = " SELECT usr "
	                + " FROM Usuarios usr "
	                + " WHERE usr.name =:name ";	               
	        Query q = em.createQuery(sql);
	        q.setParameter("name", name);
	        try {
	            return (Usuarios) q.getSingleResult();
	        } catch (Exception e) {
	            return null;
	        }
	}

}
