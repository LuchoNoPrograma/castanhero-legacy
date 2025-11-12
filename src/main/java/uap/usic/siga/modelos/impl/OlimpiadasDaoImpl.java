package uap.usic.siga.modelos.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import uap.usic.siga.entidades.OoCompeticiones;
import uap.usic.siga.entidades.OoDetalleEnfrentamientos;
import uap.usic.siga.entidades.OoEncuentros;
import uap.usic.siga.entidades.OoEnfrentamientos;
import uap.usic.siga.entidades.OoEquiposParticipantes;
import uap.usic.siga.entidades.OoEtapas;
import uap.usic.siga.entidades.OoParticipantes;
import uap.usic.siga.entidades.OoPuntuaciones;
import uap.usic.siga.entidades.OoUnidadesEducativas;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.OlimpiadasDao;

@Repository("olimpiadasDao")
public class OlimpiadasDaoImpl implements OlimpiadasDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<OoCompeticiones> listarCompeticionesJPQL() {
		String sql = "Select com from OoCompeticiones com "
				+ " Order by com.competicion ";
		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	// ============ Modulo Unidades Educativas =============
	@Transactional(rollbackOn = { ServicioException.class })
	@Override
	public List<OoUnidadesEducativas> listarUnidadesEducativasJPQL() {
		String sql = "Select uni from OoUnidadesEducativas uni "
				+ " Order by uni.nombre ";
		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public OoUnidadesEducativas registrarUnidadesEducativasSET(OoUnidadesEducativas unidadesEducativas) {
		em.persist(unidadesEducativas);
		return unidadesEducativas;
	}

	@Override
	public OoUnidadesEducativas buscarUnidadesEducativasGET(Long idUnidadEducativa) {
		String sql = " SELECT uni "
				+ " FROM OoUnidadesEducativas uni "
				+ " WHERE uni.idUnidadEducativa =:idUnidadEducativa  "
				+ " AND uni.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idUnidadEducativa", idUnidadEducativa);
		try {
			return (OoUnidadesEducativas) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional(rollbackOn = { ServicioException.class })
	@Override
	public OoUnidadesEducativas modificarUnidadesEducativasSET(OoUnidadesEducativas ooUnidadesEducativas) {
		em.persist(ooUnidadesEducativas);
		return ooUnidadesEducativas;
	}

	// ============ FIN =============

	// ============ Modulo Equipos =============

	@Transactional(rollbackOn = { ServicioException.class })
	@Override
	public List<OoEquiposParticipantes> listarEquiposParticipantesJPQL() {
		String sql = "Select eqp from OoEquiposParticipantes eqp "
				+ " Order by eqp.nombre ";
		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public OoEquiposParticipantes registrarEquiposParticipantesSET(OoEquiposParticipantes equiposParticipantes) {
		em.persist(equiposParticipantes);
		return equiposParticipantes;
	}

	@Transactional(rollbackOn = { ServicioException.class })
	@Override
	public OoEquiposParticipantes modificarEquiposParticipantesSET(OoEquiposParticipantes equiposParticipantes) {
		em.persist(equiposParticipantes);
		return equiposParticipantes;
	}

	@Override
	public OoEquiposParticipantes buscarEquiposParticipantesGET(Long idEquipoParticipante) {
		String sql = " SELECT eqp "
				+ " FROM OoEquiposParticipantes eqp "
				+ " WHERE eqp.idEquipoParticipante =:idEquipoParticipante  "
				+ " AND eqp.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idEquipoParticipante", idEquipoParticipante);
		try {
			return (OoEquiposParticipantes) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	// ============ FIN =============

	// ============ Modulo Participantes =============

	@Transactional(rollbackOn = { ServicioException.class })
	@Override
	public List<OoParticipantes> listarParticipantesJPQL() {
		String sql = "Select par from OoParticipantes par "
				+ " Order by par.edad ";
		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public OoParticipantes registrarParticipantesSET(OoParticipantes participantes) {
		em.persist(participantes);
		return participantes;
	}

	@Transactional(rollbackOn = { ServicioException.class })
	@Override
	public OoParticipantes modificarParticipantesSET(OoParticipantes ooParticipantes) {
		em.persist(ooParticipantes);
		return ooParticipantes;
	}

	@Override
	public OoParticipantes buscarParticipantesGET(Long idParticipante) {
		String sql = " SELECT par "
				+ " FROM OoParticipantes par "
				+ " WHERE par.idParticipante =:idParticipante  "
				+ " AND par.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idParticipante", idParticipante);
		try {
			return (OoParticipantes) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<OoEncuentros> listarOoEncuentrosJPQL() {
		String sql = "Select ooe from OoEncuentros ooe "
				+ " WHERE ooe.idEstado = 'A'  "
				+ " Order by ooe.encuentro ";
		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public List<OoEtapas> listarOoEtapasJPQL() {
		String sql = "Select oot from OoEtapas oot  "
				+ " WHERE oot.idEstado = 'A'  "
				+ " Order by oot.idEtapa ";
		Query q = em.createQuery(sql);
		return q.getResultList();
	}

	@Override
	public List<OoEnfrentamientos> listarOoEnfrentamientosPorIdEtapaJQPL(Long idEtapa) {
		String sql = "SELECT oop "
				+ " FROM OoEnfrentamientos oop LEFT JOIN oop.ooEtapas oot "
				+ " WHERE oot.idEtapa =:idEtapa "
				+ " AND oop.idEstado = 'A'  "
				+ " AND oot.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idEtapa", idEtapa);
		return q.getResultList();
	}

	@Override
	public OoEnfrentamientos registrarOoEnfrentamientosSET(OoEnfrentamientos ooEnfrentamientos) {
		em.persist(ooEnfrentamientos);
		return ooEnfrentamientos;
	}

	@Override
	public OoDetalleEnfrentamientos registrarOoDetalleEnfrentamientosSET(
			OoDetalleEnfrentamientos ooDetalleEnfrentamientos) {
		em.persist(ooDetalleEnfrentamientos);
		return ooDetalleEnfrentamientos;
	}

	@Override
	public OoEtapas buscarOoEtapasPorIdEtapaGET(Long idEtapa) {
		String sql = " SELECT ota "
				+ " FROM OoEtapas ota "
				+ " WHERE ota.idEtapa =:idEtapa  "
				+ " AND ota.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idEtapa", idEtapa);
		try {
			return (OoEtapas) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<OoParticipantes> listarParticipantesdEquipoParticipanteJPQL(Long idEquipoParticipante) {
		String sql = "SELECT ppt "
				+ " FROM OoParticipantes ppt LEFT JOIN ppt.ooEquiposParticipantes eqp "
				+ " WHERE eqp.idEquipoParticipante  =:idEquipoParticipante "
				+ " AND ppt.idEstado = 'A'  "
				+ " AND eqp.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idEquipoParticipante", idEquipoParticipante);
		return q.getResultList();
	}

	@Override
	public OoEnfrentamientos buscarEnfrentamientosGET(Long idEnfrentamiento) {
		String sql = " SELECT enf "
				+ " FROM OoEnfrentamientos enf "
				+ " WHERE enf.idEnfrentamientos =:idEnfrentamientos  "
				+ " AND enf.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idEnfrentamientos", idEnfrentamiento);
		try {
			return (OoEnfrentamientos) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<OoPuntuaciones> listarPuntuacionesEquipoParticipante(Long idEquipoParticipante) {
		String sql = "SELECT pnt "
				+ " FROM OoPuntuaciones pnt LEFT JOIN pnt.ooEquiposParticipantes eqt "
				+ " WHERE eqt.idEquipoParticipante =:idEquipoParticipante "
				+ " AND pnt.idEstado = 'A'  "
				+ " AND eqt.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idEquipoParticipante", idEquipoParticipante);
		return q.getResultList();
	}

	@Override
	public OoPuntuaciones registrarPuntuaciones(OoPuntuaciones ooPuntuaciones) {
		em.persist(ooPuntuaciones);
		return ooPuntuaciones;
	}

	@Override
	public OoPuntuaciones buscarPuntuaciones(Long idPuntuaciones) {
		String sql = " SELECT pnt "
				+ " FROM OoPuntuaciones pnt "
				+ " WHERE pnt.idPuntuacion =:idPuntuaciones  "
				+ " AND pnt.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idPuntuaciones", idPuntuaciones);
		try {
			return (OoPuntuaciones) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<OoPuntuaciones> listarPuntuacionesEquipoParticipanteEnfrentamiento(Long idEquipoParticipante,
			Long idEnfrentamientos) {

		String sql = " SELECT pnt "
				+ " FROM OoPuntuaciones pnt LEFT JOIN pnt.ooEnfrentamientos enf LEFT JOIN pnt.ooEquiposParticipantes equi"
				+ " WHERE enf.idEnfrentamientos =:idEnfrentamientos AND equi.idEquipoParticipante  =:idEquipoParticipante"
				+ " AND pnt.idEstado = 'A' ";
		Query q = em.createQuery(sql);
		q.setParameter("idEquipoParticipante", idEquipoParticipante);
		q.setParameter("idEnfrentamientos", idEnfrentamientos);
		return q.getResultList();
	}

}
