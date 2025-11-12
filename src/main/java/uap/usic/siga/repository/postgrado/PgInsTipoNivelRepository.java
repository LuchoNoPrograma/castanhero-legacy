package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgInsTipoNivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgInsTipoNivelRepository extends JpaRepository<PgInsTipoNivel, Long> {
}
