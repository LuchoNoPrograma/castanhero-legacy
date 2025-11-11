package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgContratoRepository extends JpaRepository<PgContrato, Long> {
}
