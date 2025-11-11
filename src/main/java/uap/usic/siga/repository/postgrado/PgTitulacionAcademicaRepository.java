package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgTitulacionAcademica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgTitulacionAcademicaRepository extends JpaRepository<PgTitulacionAcademica, Long> {
}
