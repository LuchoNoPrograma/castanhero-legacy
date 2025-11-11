package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgInsTipoEnsenyanza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgInsTipoEnsenyanzaRepository extends JpaRepository<PgInsTipoEnsenyanza, Long> {
}
