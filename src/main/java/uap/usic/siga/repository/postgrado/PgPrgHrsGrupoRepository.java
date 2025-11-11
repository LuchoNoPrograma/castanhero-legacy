package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrgHrsGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgPrgHrsGrupoRepository extends JpaRepository<PgPrgHrsGrupo, Long> {
}
