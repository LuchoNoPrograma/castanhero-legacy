package uap.usic.siga.repository.congreso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.congreso.CngComisiones;

@Repository
public interface CngComisionesRepository extends JpaRepository<CngComisiones, Long> {
}
