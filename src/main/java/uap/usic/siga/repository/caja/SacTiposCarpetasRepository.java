package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.SacTiposCarpetas;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de tipos de carpetas
 * Proporciona operaciones de acceso a datos para categorías de carpetas
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Repository
public interface SacTiposCarpetasRepository extends JpaRepository<SacTiposCarpetas, Long> {

    /**
     * Busca tipos de carpetas activos por estado
     */
    List<SacTiposCarpetas> findByIdEstado(String idEstado);

    /**
     * Busca un tipo de carpeta por nombre
     */
    Optional<SacTiposCarpetas> findBySacTipoCarpeta(String sacTipoCarpeta);
}
