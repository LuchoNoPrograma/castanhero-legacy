package uap.usic.siga.repository.olimpiadas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.olimpiadas.Competicion;

/**
 * Repositorio para la entidad Competicion
 * Proporciona operaciones CRUD y consultas personalizadas
 */
@Repository
public interface CompeticionRepository extends JpaRepository<Competicion, Long> {

    /**
     * Busca competiciones por gestión
     * @param gestion Año de la gestión
     * @return Lista de competiciones de la gestión especificada
     */
    List<Competicion> findByGestion(Integer gestion);

    /**
     * Busca competiciones por gestión y periodo
     * @param gestion Año de la gestión
     * @param periodo Periodo de la gestión
     * @return Lista de competiciones
     */
    List<Competicion> findByGestionAndPeriodo(Integer gestion, Integer periodo);
}
