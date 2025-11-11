package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgAdmision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgAdmisionRepository extends JpaRepository<PgAdmision, Long> {
}
