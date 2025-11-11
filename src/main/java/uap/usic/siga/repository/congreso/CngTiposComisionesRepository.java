package uap.usic.siga.repository.congreso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.congreso.CngTiposComisiones;

@Repository
public interface CngTiposComisionesRepository extends JpaRepository<CngTiposComisiones, Long> {
}
