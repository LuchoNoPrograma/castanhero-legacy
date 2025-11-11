package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.CjaCntUnidades;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para contabilización de gastos por unidad funcional
 * Proporciona operaciones para reportes de gastos por unidad
 *
 * @author Sistema de Caja - USIC
 */
@Repository
public interface CjaCntUnidadesRepository extends JpaRepository<CjaCntUnidades, Long> {

    /**
     * Busca contabilización por unidad funcional y gestión
     */
    Optional<CjaCntUnidades> findByInsUnidadesFuncionales_IdUnidadFuncionalAndGestion(
            Long idUnidadFuncional, Integer gestion);

    /**
     * Busca todas las contabilizaciones de una gestión
     */
    List<CjaCntUnidades> findByGestionOrderByTotalGastoDesc(Integer gestion);

    /**
     * Busca contabilizaciones por periodo y gestión
     */
    List<CjaCntUnidades> findByPeriodoAndGestion(Integer periodo, Integer gestion);
}
