package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrgHrsPeriodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgPrgHrsPeriodoRepository extends JpaRepository<PgPrgHrsPeriodo, Long> {
}
