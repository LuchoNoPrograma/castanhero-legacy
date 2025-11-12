package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrgDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgPrgDetalleRepository extends JpaRepository<PgPrgDetalle, Long> {
}
