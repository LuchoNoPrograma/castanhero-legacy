package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrgPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgPrgPlanRepository extends JpaRepository<PgPrgPlan, Long> {

    @Query("SELECT e FROM PgPrgPlan e WHERE e.idEstado = 'A'")
    List<PgPrgPlan> listarActivos();
}
