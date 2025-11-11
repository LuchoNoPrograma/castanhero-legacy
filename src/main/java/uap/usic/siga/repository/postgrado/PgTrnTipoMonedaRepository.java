package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgTrnTipoMoneda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgTrnTipoMonedaRepository extends JpaRepository<PgTrnTipoMoneda, Long> {
}
