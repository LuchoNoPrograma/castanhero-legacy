package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrgArancel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgPrgArancelRepository extends JpaRepository<PgPrgArancel, Long> {
}
