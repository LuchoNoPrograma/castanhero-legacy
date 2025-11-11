package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgMtrPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgMtrPlanRepository extends JpaRepository<PgMtrPlan, Long> {
}
