package uap.usic.siga.repository.caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.caja.CjaProveedores;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestión de proveedores
 * Proporciona operaciones de acceso a datos para proveedores
 *
 * @author Sistema de Caja - USIC
 */
@Repository
public interface CjaProveedoresRepository extends JpaRepository<CjaProveedores, Long> {

    /**
     * Busca proveedores activos por estado
     */
    List<CjaProveedores> findByIdEstado(String idEstado);

    /**
     * Busca un proveedor por sigla
     */
    Optional<CjaProveedores> findBySigla(String sigla);

    /**
     * Busca proveedores por nombre (coincidencia parcial)
     */
    List<CjaProveedores> findByProveedorContainingIgnoreCase(String proveedor);

    /**
     * Busca proveedores ordenados por nombre
     */
    List<CjaProveedores> findAllByOrderByProveedorAsc();
}
