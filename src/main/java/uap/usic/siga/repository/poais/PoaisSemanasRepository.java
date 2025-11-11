package uap.usic.siga.repository.poais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.poais.PoaisSemanas;

@Repository
public interface PoaisSemanasRepository extends JpaRepository<PoaisSemanas, Long> {
}
