package uap.usic.siga.repository.olimpiadas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uap.usic.siga.domain.olimpiadas.Encuentro;

/**
 * Repositorio para la entidad Encuentro
 * Proporciona operaciones CRUD y consultas personalizadas
 */
@Repository
public interface EncuentroRepository extends JpaRepository<Encuentro, Long> {

    /**
     * Busca encuentros por estado
     * @param estado Estado del encuentro
     * @return Lista de encuentros con el estado especificado
     */
    List<Encuentro> findByEstado(String estado);

    /**
     * Busca encuentros activos ordenados por nombre
     * @param estado Estado del encuentro
     * @return Lista de encuentros ordenados
     */
    List<Encuentro> findByEstadoOrderByEncuentroAsc(String estado);
}
