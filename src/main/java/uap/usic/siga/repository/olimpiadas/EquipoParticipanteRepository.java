package uap.usic.siga.repository.olimpiadas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.olimpiadas.EquipoParticipante;

/**
 * Repositorio para la entidad EquipoParticipante
 * Proporciona operaciones CRUD y consultas personalizadas
 */
@Repository
public interface EquipoParticipanteRepository extends JpaRepository<EquipoParticipante, Long> {

    /**
     * Busca equipos participantes por competición
     * @param idCompeticion ID de la competición
     * @return Lista de equipos participantes en la competición
     */
    @Query("SELECT ep FROM EquipoParticipante ep WHERE ep.competicion.idCompeticion = :idCompeticion")
    List<EquipoParticipante> findByCompeticionId(@Param("idCompeticion") Long idCompeticion);

    /**
     * Busca equipos participantes por unidad educativa
     * @param idUnidadEducativa ID de la unidad educativa
     * @return Lista de equipos de la unidad educativa
     */
    @Query("SELECT ep FROM EquipoParticipante ep WHERE ep.unidadEducativa.idUnidadEducativa = :idUnidadEducativa")
    List<EquipoParticipante> findByUnidadEducativaId(@Param("idUnidadEducativa") Long idUnidadEducativa);

    /**
     * Busca equipos participantes por gestión
     * @param gestion Año de la gestión
     * @return Lista de equipos participantes
     */
    List<EquipoParticipante> findByGestion(Integer gestion);

    /**
     * Busca equipos participantes por gestión y periodo
     * @param gestion Año de la gestión
     * @param periodo Periodo de la gestión
     * @return Lista de equipos participantes
     */
    List<EquipoParticipante> findByGestionAndPeriodo(Integer gestion, Integer periodo);
}
