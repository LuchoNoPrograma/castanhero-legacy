package uap.usic.siga.repository.olimpiadas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.olimpiadas.DetalleEnfrentamiento;

/**
 * Repositorio para la entidad DetalleEnfrentamiento
 * Proporciona operaciones CRUD y consultas personalizadas
 */
@Repository
public interface DetalleEnfrentamientoRepository extends JpaRepository<DetalleEnfrentamiento, Long> {

    /**
     * Busca detalles de enfrentamiento por enfrentamiento
     * @param idEnfrentamiento ID del enfrentamiento
     * @return Lista de detalles del enfrentamiento
     */
    @Query("SELECT de FROM DetalleEnfrentamiento de WHERE de.enfrentamiento.idEnfrentamiento = :idEnfrentamiento")
    List<DetalleEnfrentamiento> findByEnfrentamientoId(@Param("idEnfrentamiento") Long idEnfrentamiento);

    /**
     * Busca detalles de enfrentamiento por equipo participante
     * @param idEquipoParticipante ID del equipo participante
     * @return Lista de detalles donde participa el equipo
     */
    @Query("SELECT de FROM DetalleEnfrentamiento de WHERE de.equipoParticipante.idEquipoParticipante = :idEquipoParticipante")
    List<DetalleEnfrentamiento> findByEquipoParticipanteId(@Param("idEquipoParticipante") Long idEquipoParticipante);

    /**
     * Busca detalles por enfrentamiento y equipo
     * @param idEnfrentamiento ID del enfrentamiento
     * @param idEquipoParticipante ID del equipo participante
     * @return Lista de detalles
     */
    @Query("SELECT de FROM DetalleEnfrentamiento de WHERE de.enfrentamiento.idEnfrentamiento = :idEnfrentamiento AND de.equipoParticipante.idEquipoParticipante = :idEquipoParticipante")
    List<DetalleEnfrentamiento> findByEnfrentamientoIdAndEquipoParticipanteId(
            @Param("idEnfrentamiento") Long idEnfrentamiento,
            @Param("idEquipoParticipante") Long idEquipoParticipante);
}
