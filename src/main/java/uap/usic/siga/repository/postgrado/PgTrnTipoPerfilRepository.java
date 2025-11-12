package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgTrnTipoPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgTrnTipoPerfilRepository extends JpaRepository<PgTrnTipoPerfil, Long> {
}
