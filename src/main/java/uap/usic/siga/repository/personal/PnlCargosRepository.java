package uap.usic.siga.repository.personal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.personal.PnlCargos;

import java.util.Optional;

@Repository
public interface PnlCargosRepository extends JpaRepository<PnlCargos, Long> {

    Optional<PnlCargos> findByCargo(String cargo);
}
