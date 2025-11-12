package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgTrnPerfilConcepto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgTrnPerfilConceptoRepository extends JpaRepository<PgTrnPerfilConcepto, Long> {
}
