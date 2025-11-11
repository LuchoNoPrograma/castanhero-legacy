package uap.usic.siga.service.olimpiadas.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import uap.usic.siga.repository.olimpiadas.CompeticionRepository;
import uap.usic.siga.repository.olimpiadas.DetalleEnfrentamientoRepository;
import uap.usic.siga.repository.olimpiadas.EncuentroRepository;
import uap.usic.siga.repository.olimpiadas.EnfrentamientoRepository;
import uap.usic.siga.repository.olimpiadas.EquipoParticipanteRepository;
import uap.usic.siga.repository.olimpiadas.EtapaRepository;
import uap.usic.siga.repository.olimpiadas.ParticipanteRepository;
import uap.usic.siga.repository.olimpiadas.PreguntaRepository;
import uap.usic.siga.repository.olimpiadas.PuntuacionRepository;
import uap.usic.siga.repository.olimpiadas.UnidadEducativaRepository;
import uap.usic.siga.service.olimpiadas.OlimpiadasService;

/**
 * Implementación del servicio de Olimpiadas
 * Contiene la lógica de negocio para la gestión de olimpiadas
 */
@Service
@Transactional
public class OlimpiadasServiceImpl implements OlimpiadasService {

    @Autowired
    private CompeticionRepository competicionRepository;

    @Autowired
    private UnidadEducativaRepository unidadEducativaRepository;

    @Autowired
    private EquipoParticipanteRepository equipoParticipanteRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private EncuentroRepository encuentroRepository;

    @Autowired
    private EtapaRepository etapaRepository;

    @Autowired
    private EnfrentamientoRepository enfrentamientoRepository;

    @Autowired
    private DetalleEnfrentamientoRepository detalleEnfrentamientoRepository;

    @Autowired
    private PuntuacionRepository puntuacionRepository;

    @Autowired
    private PreguntaRepository preguntaRepository;

    // ============ Implementación de Competiciones =============
    @Override
    @Transactional(readOnly = true)
    public List<Competicion> listarCompeticiones() {
        return competicionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Competicion buscarCompeticion(Long idCompeticion) {
        return competicionRepository.findById(idCompeticion)
                .orElseThrow(() -> new RuntimeException("Competición no encontrada con ID: " + idCompeticion));
    }

    @Override
    public Competicion registrarCompeticion(Competicion competicion) {
        return competicionRepository.save(competicion);
    }

    @Override
    public Competicion modificarCompeticion(Competicion competicion) {
        if (!competicionRepository.existsById(competicion.getIdCompeticion())) {
            throw new RuntimeException("Competición no encontrada con ID: " + competicion.getIdCompeticion());
        }
        return competicionRepository.save(competicion);
    }

    // ============ Implementación de Unidades Educativas =============
    @Override
    @Transactional(readOnly = true)
    public List<UnidadEducativa> listarUnidadesEducativas() {
        return unidadEducativaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UnidadEducativa buscarUnidadEducativa(Long idUnidadEducativa) {
        return unidadEducativaRepository.findById(idUnidadEducativa)
                .orElseThrow(() -> new RuntimeException("Unidad educativa no encontrada con ID: " + idUnidadEducativa));
    }

    @Override
    public UnidadEducativa registrarUnidadEducativa(UnidadEducativa unidadEducativa) {
        return unidadEducativaRepository.save(unidadEducativa);
    }

    @Override
    public UnidadEducativa modificarUnidadEducativa(UnidadEducativa unidadEducativa) {
        if (!unidadEducativaRepository.existsById(unidadEducativa.getIdUnidadEducativa())) {
            throw new RuntimeException("Unidad educativa no encontrada con ID: " + unidadEducativa.getIdUnidadEducativa());
        }
        return unidadEducativaRepository.save(unidadEducativa);
    }

    // ============ Implementación de Equipos Participantes =============
    @Override
    @Transactional(readOnly = true)
    public List<EquipoParticipante> listarEquiposParticipantes() {
        return equipoParticipanteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public EquipoParticipante buscarEquipoParticipante(Long idEquipoParticipante) {
        return equipoParticipanteRepository.findById(idEquipoParticipante)
                .orElseThrow(() -> new RuntimeException("Equipo participante no encontrado con ID: " + idEquipoParticipante));
    }

    @Override
    public EquipoParticipante registrarEquipoParticipante(EquipoParticipante equipoParticipante) {
        return equipoParticipanteRepository.save(equipoParticipante);
    }

    @Override
    public EquipoParticipante modificarEquipoParticipante(EquipoParticipante equipoParticipante) {
        if (!equipoParticipanteRepository.existsById(equipoParticipante.getIdEquipoParticipante())) {
            throw new RuntimeException("Equipo participante no encontrado con ID: " + equipoParticipante.getIdEquipoParticipante());
        }
        return equipoParticipanteRepository.save(equipoParticipante);
    }

    // ============ Implementación de Participantes =============
    @Override
    @Transactional(readOnly = true)
    public List<Participante> listarParticipantes() {
        return participanteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Participante buscarParticipante(Long idParticipante) {
        return participanteRepository.findById(idParticipante)
                .orElseThrow(() -> new RuntimeException("Participante no encontrado con ID: " + idParticipante));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Participante> listarParticipantesPorEquipo(Long idEquipoParticipante) {
        return participanteRepository.findByEquipoParticipanteId(idEquipoParticipante);
    }

    @Override
    public Participante registrarParticipante(Participante participante) {
        return participanteRepository.save(participante);
    }

    @Override
    public Participante modificarParticipante(Participante participante) {
        if (!participanteRepository.existsById(participante.getIdParticipante())) {
            throw new RuntimeException("Participante no encontrado con ID: " + participante.getIdParticipante());
        }
        return participanteRepository.save(participante);
    }

    // ============ Implementación de Encuentros =============
    @Override
    @Transactional(readOnly = true)
    public List<Encuentro> listarEncuentros() {
        return encuentroRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Encuentro buscarEncuentro(Long idEncuentro) {
        return encuentroRepository.findById(idEncuentro)
                .orElseThrow(() -> new RuntimeException("Encuentro no encontrado con ID: " + idEncuentro));
    }

    // ============ Implementación de Etapas =============
    @Override
    @Transactional(readOnly = true)
    public List<Etapa> listarEtapas() {
        return etapaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Etapa buscarEtapa(Long idEtapa) {
        return etapaRepository.findById(idEtapa)
                .orElseThrow(() -> new RuntimeException("Etapa no encontrada con ID: " + idEtapa));
    }

    // ============ Implementación de Enfrentamientos =============
    @Override
    @Transactional(readOnly = true)
    public List<Enfrentamiento> listarEnfrentamientosPorEtapa(Long idEtapa) {
        return enfrentamientoRepository.findByEtapaId(idEtapa);
    }

    @Override
    @Transactional(readOnly = true)
    public Enfrentamiento buscarEnfrentamiento(Long idEnfrentamiento) {
        return enfrentamientoRepository.findById(idEnfrentamiento)
                .orElseThrow(() -> new RuntimeException("Enfrentamiento no encontrado con ID: " + idEnfrentamiento));
    }

    @Override
    public Enfrentamiento registrarEnfrentamiento(Enfrentamiento enfrentamiento) {
        return enfrentamientoRepository.save(enfrentamiento);
    }

    @Override
    public Enfrentamiento modificarEnfrentamiento(Enfrentamiento enfrentamiento) {
        if (!enfrentamientoRepository.existsById(enfrentamiento.getIdEnfrentamiento())) {
            throw new RuntimeException("Enfrentamiento no encontrado con ID: " + enfrentamiento.getIdEnfrentamiento());
        }
        return enfrentamientoRepository.save(enfrentamiento);
    }

    // ============ Implementación de Detalle Enfrentamiento =============
    @Override
    public DetalleEnfrentamiento registrarDetalleEnfrentamiento(DetalleEnfrentamiento detalleEnfrentamiento) {
        return detalleEnfrentamientoRepository.save(detalleEnfrentamiento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleEnfrentamiento> listarDetallesPorEnfrentamiento(Long idEnfrentamiento) {
        return detalleEnfrentamientoRepository.findByEnfrentamientoId(idEnfrentamiento);
    }

    // ============ Implementación de Puntuaciones =============
    @Override
    @Transactional(readOnly = true)
    public List<Puntuacion> listarPuntuacionesPorEquipo(Long idEquipoParticipante) {
        return puntuacionRepository.findByEquipoParticipanteId(idEquipoParticipante);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Puntuacion> listarPuntuacionesPorEquipoYEnfrentamiento(Long idEquipoParticipante, Long idEnfrentamiento) {
        return puntuacionRepository.findByEquipoParticipanteIdAndEnfrentamientoId(idEquipoParticipante, idEnfrentamiento);
    }

    @Override
    @Transactional(readOnly = true)
    public Puntuacion buscarPuntuacion(Long idPuntuacion) {
        return puntuacionRepository.findById(idPuntuacion)
                .orElseThrow(() -> new RuntimeException("Puntuación no encontrada con ID: " + idPuntuacion));
    }

    @Override
    public Puntuacion registrarPuntuacion(Puntuacion puntuacion) {
        return puntuacionRepository.save(puntuacion);
    }

    @Override
    public Puntuacion modificarPuntuacion(Puntuacion puntuacion) {
        if (!puntuacionRepository.existsById(puntuacion.getIdPuntuacion())) {
            throw new RuntimeException("Puntuación no encontrada con ID: " + puntuacion.getIdPuntuacion());
        }
        return puntuacionRepository.save(puntuacion);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer calcularTotalPuntos(Long idEquipoParticipante, Long idEnfrentamiento) {
        return puntuacionRepository.sumPuntajeByEquipoAndEnfrentamiento(idEquipoParticipante, idEnfrentamiento);
    }

    // ============ Implementación de Preguntas =============
    @Override
    @Transactional(readOnly = true)
    public List<Pregunta> listarPreguntasPorEtapa(Long idEtapa) {
        return preguntaRepository.findByEtapaId(idEtapa);
    }

    @Override
    @Transactional(readOnly = true)
    public Pregunta buscarPregunta(Long idPregunta) {
        return preguntaRepository.findById(idPregunta)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada con ID: " + idPregunta));
    }

    @Override
    public Pregunta registrarPregunta(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @Override
    public Pregunta modificarPregunta(Pregunta pregunta) {
        if (!preguntaRepository.existsById(pregunta.getIdPregunta())) {
            throw new RuntimeException("Pregunta no encontrada con ID: " + pregunta.getIdPregunta());
        }
        return preguntaRepository.save(pregunta);
    }
}
