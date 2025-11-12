package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgFclPrograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgFclProgramaRepository extends JpaRepository<PgFclPrograma, Long> {
}
