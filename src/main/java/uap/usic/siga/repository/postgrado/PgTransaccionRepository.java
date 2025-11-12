package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgTransaccionRepository extends JpaRepository<PgTransaccion, Long> {
}
