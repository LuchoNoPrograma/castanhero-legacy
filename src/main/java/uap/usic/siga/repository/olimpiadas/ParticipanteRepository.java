package uap.usic.siga.repository.olimpiadas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.olimpiadas.Participante;

/**
 * Repositorio para la entidad Participante
 * Proporciona operaciones CRUD y consultas personalizadas
 */
@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

    /**
     * Busca participantes por equipo participante
     * @param idEquipoParticipante ID del equipo participante
     * @return Lista de participantes del equipo
     */
    @Query("SELECT p FROM Participante p WHERE p.equipoParticipante.idEquipoParticipante = :idEquipoParticipante")
    List<Participante> findByEquipoParticipanteId(@Param("idEquipoParticipante") Long idEquipoParticipante);

    /**
     * Busca participantes por persona
     * @param idPersona ID de la persona
     * @return Lista de participaciones de la persona
     */
    @Query("SELECT p FROM Participante p WHERE p.persona.idPersona = :idPersona")
    List<Participante> findByPersonaId(@Param("idPersona") Long idPersona);

    /**
     * Busca participantes por gestión
     * @param gestion Año de la gestión
     * @return Lista de participantes de la gestión especificada
     */
    List<Participante> findByGestion(Integer gestion);

    /**
     * Busca participantes por gestión y periodo
     * @param gestion Año de la gestión
     * @param periodo Periodo de la gestión
     * @return Lista de participantes
     */
    List<Participante> findByGestionAndPeriodo(Integer gestion, Integer periodo);
}
