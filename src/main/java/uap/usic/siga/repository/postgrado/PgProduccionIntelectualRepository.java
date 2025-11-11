package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgProduccionIntelectual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgProduccionIntelectualRepository extends JpaRepository<PgProduccionIntelectual, Long> {
}
