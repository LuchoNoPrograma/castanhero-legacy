package uap.usic.siga.repository.personal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.personal.PnlTiposAdministrativos;

@Repository
public interface PnlTiposAdministrativosRepository extends JpaRepository<PnlTiposAdministrativos, Long> {
}
