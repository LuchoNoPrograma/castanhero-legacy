package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.CjaPedidosCertificados;

import java.util.Date;
import java.util.List;

/**
 * Repositorio para gestión de certificados de pedidos
 * Proporciona operaciones de acceso a datos para certificaciones
 *
 * @author Sistema de Caja - USIC
 */
@Repository
public interface CjaPedidosCertificadosRepository extends JpaRepository<CjaPedidosCertificados, Long> {

    /**
     * Busca certificados por rango de fechas
     */
    List<CjaPedidosCertificados> findByFecCertificadoBetween(Date fecInicio, Date fecFin);

    /**
     * Busca certificados por gestión
     */
    List<CjaPedidosCertificados> findByGestionOrderByFecCertificadoDesc(Integer gestion);

    /**
     * Busca certificados por estado
     */
    List<CjaPedidosCertificados> findByIdEstadoOrderByFecCertificadoDesc(String idEstado);
}
