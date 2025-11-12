package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgFclPrgIntermedio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgFclPrgIntermedioRepository extends JpaRepository<PgFclPrgIntermedio, Long> {
}
