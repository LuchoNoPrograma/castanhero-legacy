package uap.usic.siga.repository.olimpiadas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.olimpiadas.Pregunta;

/**
 * Repositorio para la entidad Pregunta
 * Proporciona operaciones CRUD y consultas personalizadas
 */
@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {

    /**
     * Busca preguntas por etapa
     * @param idEtapa ID de la etapa
     * @return Lista de preguntas de la etapa
     */
    @Query("SELECT p FROM Pregunta p WHERE p.etapa.idEtapa = :idEtapa")
    List<Pregunta> findByEtapaId(@Param("idEtapa") Long idEtapa);

    /**
     * Busca preguntas por gestión
     * @param gestion Año de la gestión
     * @return Lista de preguntas de la gestión especificada
     */
    List<Pregunta> findByGestion(Integer gestion);

    /**
     * Busca preguntas por gestión y periodo
     * @param gestion Año de la gestión
     * @param periodo Periodo de la gestión
     * @return Lista de preguntas
     */
    List<Pregunta> findByGestionAndPeriodo(Integer gestion, Integer periodo);
}
