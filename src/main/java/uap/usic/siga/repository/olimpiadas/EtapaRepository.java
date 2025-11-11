package uap.usic.siga.repository.olimpiadas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.olimpiadas.Etapa;

/**
 * Repositorio para la entidad Etapa
 * Proporciona operaciones CRUD y consultas personalizadas
 */
@Repository
public interface EtapaRepository extends JpaRepository<Etapa, Long> {

    /**
     * Busca etapas por estado
     * @param estado Estado de la etapa
     * @return Lista de etapas con el estado especificado
     */
    List<Etapa> findByEstado(String estado);

    /**
     * Busca etapas activas ordenadas por nombre
     * @param estado Estado de la etapa
     * @return Lista de etapas ordenadas
     */
    List<Etapa> findByEstadoOrderByEtapaAsc(String estado);
}
