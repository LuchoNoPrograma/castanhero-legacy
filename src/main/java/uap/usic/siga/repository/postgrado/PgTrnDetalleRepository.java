package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgTrnDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgTrnDetalleRepository extends JpaRepository<PgTrnDetalle, Long> {
}
