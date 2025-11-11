package uap.usic.siga.modelos;

import java.util.List;

import uap.usic.siga.entidades.OoCompeticiones;
import uap.usic.siga.entidades.OoEncuentros;
import uap.usic.siga.entidades.OoEnfrentamientos;
import uap.usic.siga.entidades.OoEquiposParticipantes;
import uap.usic.siga.entidades.OoEtapas;
import uap.usic.siga.entidades.OoParticipantes;
import uap.usic.siga.entidades.OoPuntuaciones;
import uap.usic.siga.entidades.OoUnidadesEducativas;

public interface OlimpiadasDao {

	public List<OoCompeticiones> listarCompeticionesJPQL();

	// ============ Modulo Unidades Educativas =============
	public List<OoUnidadesEducativas> listarUnidadesEducativasJPQL();

	public OoUnidadesEducativas registrarUnidadesEducativasSET(OoUnidadesEducativas unidadesEducativas);

	public OoUnidadesEducativas modificarUnidadesEducativasSET(OoUnidadesEducativas ooUnidadesEducativas);

	public OoUnidadesEducativas buscarUnidadesEducativasGET(Long idUnidadEducativa);
	// ============ FIN =============

	// ============ Modulo Puntuaciones =============
	public List<OoPuntuaciones> listarPuntuacionesEquipoParticipante(Long idEquipoParticipante);
	public List<OoPuntuaciones> listarPuntuacionesEquipoParticipanteEnfrentamiento(Long idEquipoParticipante, Long idEnfrentamientos);
	
	public OoPuntuaciones registrarPuntuaciones(OoPuntuaciones ooPuntuaciones);
	public OoPuntuaciones buscarPuntuaciones(Long idPuntuaciones);

	// ============ Modulo Equipos =============
	public List<OoEquiposParticipantes> listarEquiposParticipantesJPQL();

	public OoEquiposParticipantes registrarEquiposParticipantesSET(OoEquiposParticipantes equiposParticipantes);

	public OoEquiposParticipantes modificarEquiposParticipantesSET(OoEquiposParticipantes equiposParticipantes);

	public OoEquiposParticipantes buscarEquiposParticipantesGET(Long idEquipoParticipante);

	// ============ Modulo Participantes =============
	public List<OoParticipantes> listarParticipantesJPQL();

	public OoParticipantes registrarParticipantesSET(OoParticipantes participantes);

	public OoParticipantes modificarParticipantesSET(OoParticipantes ooParticipantes);

	public OoParticipantes buscarParticipantesGET(Long idParticipante);

	// ============ Modulo Enfrentamientos =============
	public List<OoEncuentros> listarOoEncuentrosJPQL();

	public List<OoEtapas> listarOoEtapasJPQL();

	public List<OoEnfrentamientos> listarOoEnfrentamientosPorIdEtapaJQPL(Long idEtapa);

	// ===================
	public OoEnfrentamientos registrarOoEnfrentamientosSET(OoEnfrentamientos ooEnfrentamientos);
	public OoEnfrentamientos buscarEnfrentamientosGET(Long idEnfrentamiento);
	public uap.usic.siga.entidades.OoDetalleEnfrentamientos registrarOoDetalleEnfrentamientosSET(
			uap.usic.siga.entidades.OoDetalleEnfrentamientos ooDetalleEnfrentamientos);

	public OoEtapas buscarOoEtapasPorIdEtapaGET(Long idEtapa);

	public List<OoParticipantes> listarParticipantesdEquipoParticipanteJPQL(Long idEquipoParticipante);

}
