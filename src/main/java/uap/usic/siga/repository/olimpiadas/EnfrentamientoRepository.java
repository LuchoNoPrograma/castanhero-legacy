package uap.usic.siga.repository.olimpiadas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.olimpiadas.Enfrentamiento;

/**
 * Repositorio para la entidad Enfrentamiento
 * Proporciona operaciones CRUD y consultas personalizadas
 */
@Repository
public interface EnfrentamientoRepository extends JpaRepository<Enfrentamiento, Long> {

    /**
     * Busca enfrentamientos por etapa
     * @param idEtapa ID de la etapa
     * @return Lista de enfrentamientos de la etapa
     */
    @Query("SELECT e FROM Enfrentamiento e WHERE e.etapa.idEtapa = :idEtapa")
    List<Enfrentamiento> findByEtapaId(@Param("idEtapa") Long idEtapa);

    /**
     * Busca enfrentamientos por encuentro
     * @param idEncuentro ID del encuentro
     * @return Lista de enfrentamientos del encuentro
     */
    @Query("SELECT e FROM Enfrentamiento e WHERE e.encuentro.idEncuentro = :idEncuentro")
    List<Enfrentamiento> findByEncuentroId(@Param("idEncuentro") Long idEncuentro);

    /**
     * Busca enfrentamientos por estado
     * @param estado Estado del enfrentamiento
     * @return Lista de enfrentamientos con el estado especificado
     */
    List<Enfrentamiento> findByEstado(String estado);

    /**
     * Busca enfrentamientos por etapa y estado
     * @param idEtapa ID de la etapa
     * @param estado Estado del enfrentamiento
     * @return Lista de enfrentamientos
     */
    @Query("SELECT e FROM Enfrentamiento e WHERE e.etapa.idEtapa = :idEtapa AND e.estado = :estado")
    List<Enfrentamiento> findByEtapaIdAndEstado(@Param("idEtapa") Long idEtapa, @Param("estado") String estado);
}
