package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgCntPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgCntPagoRepository extends JpaRepository<PgCntPago, Long> {
}
