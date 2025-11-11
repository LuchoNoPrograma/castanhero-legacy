package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgTipoDescuento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgTipoDescuentoRepository extends JpaRepository<PgTipoDescuento, Long> {

    @Query("SELECT e FROM PgTipoDescuento e WHERE e.idEstado = 'A'")
    List<PgTipoDescuento> listarActivos();
}
