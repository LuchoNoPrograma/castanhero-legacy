package uap.usic.siga.repository.electoral;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.electoral.EscrutinioActas;

@Repository
public interface EscrutinioActasRepository extends JpaRepository<EscrutinioActas, Long> {
}
