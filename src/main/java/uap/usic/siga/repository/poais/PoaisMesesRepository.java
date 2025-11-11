package uap.usic.siga.repository.poais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.poais.PoaisMeses;

@Repository
public interface PoaisMesesRepository extends JpaRepository<PoaisMeses, Long> {
}
