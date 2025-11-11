package uap.usic.siga.repository.olimpiadas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.olimpiadas.Puntuacion;

/**
 * Repositorio para la entidad Puntuacion
 * Proporciona operaciones CRUD y consultas personalizadas
 */
@Repository
public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long> {

    /**
     * Busca puntuaciones por equipo participante
     * @param idEquipoParticipante ID del equipo participante
     * @return Lista de puntuaciones del equipo
     */
    @Query("SELECT p FROM Puntuacion p WHERE p.equipoParticipante.idEquipoParticipante = :idEquipoParticipante")
    List<Puntuacion> findByEquipoParticipanteId(@Param("idEquipoParticipante") Long idEquipoParticipante);

    /**
     * Busca puntuaciones por equipo y enfrentamiento
     * @param idEquipoParticipante ID del equipo participante
     * @param idEnfrentamiento ID del enfrentamiento
     * @return Lista de puntuaciones
     */
    @Query("SELECT p FROM Puntuacion p WHERE p.equipoParticipante.idEquipoParticipante = :idEquipoParticipante AND p.enfrentamiento.idEnfrentamiento = :idEnfrentamiento")
    List<Puntuacion> findByEquipoParticipanteIdAndEnfrentamientoId(
            @Param("idEquipoParticipante") Long idEquipoParticipante,
            @Param("idEnfrentamiento") Long idEnfrentamiento);

    /**
     * Busca puntuaciones por participante
     * @param idParticipante ID del participante
     * @return Lista de puntuaciones del participante
     */
    @Query("SELECT p FROM Puntuacion p WHERE p.participante.idParticipante = :idParticipante")
    List<Puntuacion> findByParticipanteId(@Param("idParticipante") Long idParticipante);

    /**
     * Busca puntuaciones por enfrentamiento
     * @param idEnfrentamiento ID del enfrentamiento
     * @return Lista de puntuaciones del enfrentamiento
     */
    @Query("SELECT p FROM Puntuacion p WHERE p.enfrentamiento.idEnfrentamiento = :idEnfrentamiento")
    List<Puntuacion> findByEnfrentamientoId(@Param("idEnfrentamiento") Long idEnfrentamiento);

    /**
     * Calcula el total de puntos de un equipo en un enfrentamiento
     * @param idEquipoParticipante ID del equipo participante
     * @param idEnfrentamiento ID del enfrentamiento
     * @return Suma total de puntos
     */
    @Query("SELECT COALESCE(SUM(p.puntaje), 0) FROM Puntuacion p WHERE p.equipoParticipante.idEquipoParticipante = :idEquipoParticipante AND p.enfrentamiento.idEnfrentamiento = :idEnfrentamiento")
    Integer sumPuntajeByEquipoAndEnfrentamiento(
            @Param("idEquipoParticipante") Long idEquipoParticipante,
            @Param("idEnfrentamiento") Long idEnfrentamiento);
}
