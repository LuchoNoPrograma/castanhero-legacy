package uap.usic.siga.repository.olimpiadas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.olimpiadas.UnidadEducativa;

/**
 * Repositorio para la entidad UnidadEducativa
 * Proporciona operaciones CRUD y consultas personalizadas
 */
@Repository
public interface UnidadEducativaRepository extends JpaRepository<UnidadEducativa, Long> {

    /**
     * Busca unidades educativas por gestión
     * @param gestion Año de la gestión
     * @return Lista de unidades educativas de la gestión especificada
     */
    List<UnidadEducativa> findByGestion(Integer gestion);

    /**
     * Busca unidades educativas por gestión y periodo
     * @param gestion Año de la gestión
     * @param periodo Periodo de la gestión
     * @return Lista de unidades educativas
     */
    List<UnidadEducativa> findByGestionAndPeriodo(Integer gestion, Integer periodo);

    /**
     * Busca unidades educativas por nombre
     * @param nombre Nombre de la unidad educativa
     * @return Lista de unidades educativas que contienen el nombre especificado
     */
    List<UnidadEducativa> findByNombreContainingIgnoreCase(String nombre);
}
