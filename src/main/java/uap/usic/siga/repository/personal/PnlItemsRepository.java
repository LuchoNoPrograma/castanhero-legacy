package uap.usic.siga.repository.personal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.personal.PnlItems;

@Repository
public interface PnlItemsRepository extends JpaRepository<PnlItems, Long> {
}
