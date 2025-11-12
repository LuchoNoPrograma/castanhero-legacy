package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgInsTitulacionAcademica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgInsTitulacionAcademicaRepository extends JpaRepository<PgInsTitulacionAcademica, Long> {
}
