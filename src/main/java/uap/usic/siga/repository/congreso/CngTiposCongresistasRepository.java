package uap.usic.siga.repository.congreso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.congreso.CngTiposCongresistas;

@Repository
public interface CngTiposCongresistasRepository extends JpaRepository<CngTiposCongresistas, Long> {
}
