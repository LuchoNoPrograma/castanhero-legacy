package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.SacCarpetas;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de carpetas de archivo
 * Proporciona operaciones de acceso a datos para carpetas físicas
 *
 * @author Sistema de Archivo Contable - USIC
 */
@Repository
public interface SacCarpetasRepository extends JpaRepository<SacCarpetas, Long> {

    /**
     * Busca carpetas por estante
     */
    List<SacCarpetas> findBySacEstantes_IdSacEstante(Long idSacEstante);

    /**
     * Busca carpetas por tipo de carpeta
     */
    List<SacCarpetas> findBySacTiposCarpetas_IdSacTipoCarpeta(Long idSacTipoCarpeta);

    /**
     * Busca carpetas por mes y gestión
     */
    List<SacCarpetas> findBySisMeses_IdMesAndGestion(Long idMes, Integer gestion);

    /**
     * Busca una carpeta por código
     */
    Optional<SacCarpetas> findBySacCodigoCarpeta(String sacCodigoCarpeta);

    /**
     * Busca carpetas por gestión ordenadas por número
     */
    List<SacCarpetas> findByGestionOrderBySacNumeroCarpetaAsc(Integer gestion);

    /**
     * Busca carpetas por usuario y gestión
     */
    List<SacCarpetas> findByUsuarios_IdUsuarioAndGestion(Long idUsuario, Integer gestion);
}
