package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgTrnConcepto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgTrnConceptoRepository extends JpaRepository<PgTrnConcepto, Long> {
}
