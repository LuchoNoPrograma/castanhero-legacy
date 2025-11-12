package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgTrnPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgTrnPerfilRepository extends JpaRepository<PgTrnPerfil, Long> {
}
