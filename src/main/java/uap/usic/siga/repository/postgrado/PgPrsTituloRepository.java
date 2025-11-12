package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrsTitulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgPrsTituloRepository extends JpaRepository<PgPrsTitulo, Long> {
}
