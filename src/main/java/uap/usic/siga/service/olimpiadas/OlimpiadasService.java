package uap.usic.siga.service.olimpiadas;

import java.util.List;

import uap.usic.siga.domain.olimpiadas.Competicion;
import uap.usic.siga.domain.olimpiadas.DetalleEnfrentamiento;
import uap.usic.siga.domain.olimpiadas.Encuentro;
import uap.usic.siga.domain.olimpiadas.Enfrentamiento;
import uap.usic.siga.domain.olimpiadas.EquipoParticipante;
import uap.usic.siga.domain.olimpiadas.Etapa;
import uap.usic.siga.domain.olimpiadas.Participante;
import uap.usic.siga.domain.olimpiadas.Pregunta;
import uap.usic.siga.domain.olimpiadas.Puntuacion;
import uap.usic.siga.domain.olimpiadas.UnidadEducativa;

/**
 * Interfaz de servicio para el módulo de Olimpiadas
 * Define las operaciones de negocio para la gestión de olimpiadas
 */
public interface OlimpiadasService {

    // ============ Servicios de Competiciones =============
    /**
     * Lista todas las competiciones
     * @return Lista de competiciones
     */
    List<Competicion> listarCompeticiones();

    /**
     * Busca una competición por ID
     * @param idCompeticion ID de la competición
     * @return Competición encontrada
     */
    Competicion buscarCompeticion(Long idCompeticion);

    /**
     * Registra una nueva competición
     * @param competicion Competición a registrar
     * @return Competición registrada
     */
    Competicion registrarCompeticion(Competicion competicion);

    /**
     * Modifica una competición existente
     * @param competicion Competición a modificar
     * @return Competición modificada
     */
    Competicion modificarCompeticion(Competicion competicion);

    // ============ Servicios de Unidades Educativas =============
    /**
     * Lista todas las unidades educativas
     * @return Lista de unidades educativas
     */
    List<UnidadEducativa> listarUnidadesEducativas();

    /**
     * Busca una unidad educativa por ID
     * @param idUnidadEducativa ID de la unidad educativa
     * @return Unidad educativa encontrada
     */
    UnidadEducativa buscarUnidadEducativa(Long idUnidadEducativa);

    /**
     * Registra una nueva unidad educativa
     * @param unidadEducativa Unidad educativa a registrar
     * @return Unidad educativa registrada
     */
    UnidadEducativa registrarUnidadEducativa(UnidadEducativa unidadEducativa);

    /**
     * Modifica una unidad educativa existente
     * @param unidadEducativa Unidad educativa a modificar
     * @return Unidad educativa modificada
     */
    UnidadEducativa modificarUnidadEducativa(UnidadEducativa unidadEducativa);

    // ============ Servicios de Equipos Participantes =============
    /**
     * Lista todos los equipos participantes
     * @return Lista de equipos participantes
     */
    List<EquipoParticipante> listarEquiposParticipantes();

    /**
     * Busca un equipo participante por ID
     * @param idEquipoParticipante ID del equipo participante
     * @return Equipo participante encontrado
     */
    EquipoParticipante buscarEquipoParticipante(Long idEquipoParticipante);

    /**
     * Registra un nuevo equipo participante
     * @param equipoParticipante Equipo participante a registrar
     * @return Equipo participante registrado
     */
    EquipoParticipante registrarEquipoParticipante(EquipoParticipante equipoParticipante);

    /**
     * Modifica un equipo participante existente
     * @param equipoParticipante Equipo participante a modificar
     * @return Equipo participante modificado
     */
    EquipoParticipante modificarEquipoParticipante(EquipoParticipante equipoParticipante);

    // ============ Servicios de Participantes =============
    /**
     * Lista todos los participantes
     * @return Lista de participantes
     */
    List<Participante> listarParticipantes();

    /**
     * Busca un participante por ID
     * @param idParticipante ID del participante
     * @return Participante encontrado
     */
    Participante buscarParticipante(Long idParticipante);

    /**
     * Lista participantes de un equipo específico
     * @param idEquipoParticipante ID del equipo participante
     * @return Lista de participantes del equipo
     */
    List<Participante> listarParticipantesPorEquipo(Long idEquipoParticipante);

    /**
     * Registra un nuevo participante
     * @param participante Participante a registrar
     * @return Participante registrado
     */
    Participante registrarParticipante(Participante participante);

    /**
     * Modifica un participante existente
     * @param participante Participante a modificar
     * @return Participante modificado
     */
    Participante modificarParticipante(Participante participante);

    // ============ Servicios de Encuentros =============
    /**
     * Lista todos los encuentros
     * @return Lista de encuentros
     */
    List<Encuentro> listarEncuentros();

    /**
     * Busca un encuentro por ID
     * @param idEncuentro ID del encuentro
     * @return Encuentro encontrado
     */
    Encuentro buscarEncuentro(Long idEncuentro);

    // ============ Servicios de Etapas =============
    /**
     * Lista todas las etapas
     * @return Lista de etapas
     */
    List<Etapa> listarEtapas();

    /**
     * Busca una etapa por ID
     * @param idEtapa ID de la etapa
     * @return Etapa encontrada
     */
    Etapa buscarEtapa(Long idEtapa);

    // ============ Servicios de Enfrentamientos =============
    /**
     * Lista enfrentamientos por etapa
     * @param idEtapa ID de la etapa
     * @return Lista de enfrentamientos de la etapa
     */
    List<Enfrentamiento> listarEnfrentamientosPorEtapa(Long idEtapa);

    /**
     * Busca un enfrentamiento por ID
     * @param idEnfrentamiento ID del enfrentamiento
     * @return Enfrentamiento encontrado
     */
    Enfrentamiento buscarEnfrentamiento(Long idEnfrentamiento);

    /**
     * Registra un nuevo enfrentamiento
     * @param enfrentamiento Enfrentamiento a registrar
     * @return Enfrentamiento registrado
     */
    Enfrentamiento registrarEnfrentamiento(Enfrentamiento enfrentamiento);

    /**
     * Modifica un enfrentamiento existente
     * @param enfrentamiento Enfrentamiento a modificar
     * @return Enfrentamiento modificado
     */
    Enfrentamiento modificarEnfrentamiento(Enfrentamiento enfrentamiento);

    // ============ Servicios de Detalle Enfrentamiento =============
    /**
     * Registra un nuevo detalle de enfrentamiento
     * @param detalleEnfrentamiento Detalle a registrar
     * @return Detalle de enfrentamiento registrado
     */
    DetalleEnfrentamiento registrarDetalleEnfrentamiento(DetalleEnfrentamiento detalleEnfrentamiento);

    /**
     * Lista detalles de un enfrentamiento
     * @param idEnfrentamiento ID del enfrentamiento
     * @return Lista de detalles del enfrentamiento
     */
    List<DetalleEnfrentamiento> listarDetallesPorEnfrentamiento(Long idEnfrentamiento);

    // ============ Servicios de Puntuaciones =============
    /**
     * Lista puntuaciones de un equipo participante
     * @param idEquipoParticipante ID del equipo participante
     * @return Lista de puntuaciones del equipo
     */
    List<Puntuacion> listarPuntuacionesPorEquipo(Long idEquipoParticipante);

    /**
     * Lista puntuaciones de un equipo en un enfrentamiento específico
     * @param idEquipoParticipante ID del equipo participante
     * @param idEnfrentamiento ID del enfrentamiento
     * @return Lista de puntuaciones
     */
    List<Puntuacion> listarPuntuacionesPorEquipoYEnfrentamiento(Long idEquipoParticipante, Long idEnfrentamiento);

    /**
     * Busca una puntuación por ID
     * @param idPuntuacion ID de la puntuación
     * @return Puntuación encontrada
     */
    Puntuacion buscarPuntuacion(Long idPuntuacion);

    /**
     * Registra una nueva puntuación
     * @param puntuacion Puntuación a registrar
     * @return Puntuación registrada
     */
    Puntuacion registrarPuntuacion(Puntuacion puntuacion);

    /**
     * Modifica una puntuación existente
     * @param puntuacion Puntuación a modificar
     * @return Puntuación modificada
     */
    Puntuacion modificarPuntuacion(Puntuacion puntuacion);

    /**
     * Calcula el total de puntos de un equipo en un enfrentamiento
     * @param idEquipoParticipante ID del equipo participante
     * @param idEnfrentamiento ID del enfrentamiento
     * @return Total de puntos
     */
    Integer calcularTotalPuntos(Long idEquipoParticipante, Long idEnfrentamiento);

    // ============ Servicios de Preguntas =============
    /**
     * Lista preguntas por etapa
     * @param idEtapa ID de la etapa
     * @return Lista de preguntas de la etapa
     */
    List<Pregunta> listarPreguntasPorEtapa(Long idEtapa);

    /**
     * Busca una pregunta por ID
     * @param idPregunta ID de la pregunta
     * @return Pregunta encontrada
     */
    Pregunta buscarPregunta(Long idPregunta);

    /**
     * Registra una nueva pregunta
     * @param pregunta Pregunta a registrar
     * @return Pregunta registrada
     */
    Pregunta registrarPregunta(Pregunta pregunta);

    /**
     * Modifica una pregunta existente
     * @param pregunta Pregunta a modificar
     * @return Pregunta modificada
     */
    Pregunta modificarPregunta(Pregunta pregunta);
}
