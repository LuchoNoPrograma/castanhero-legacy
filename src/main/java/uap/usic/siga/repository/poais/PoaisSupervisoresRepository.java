package uap.usic.siga.repository.poais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.poais.PoaisSupervisores;

@Repository
public interface PoaisSupervisoresRepository extends JpaRepository<PoaisSupervisores, Long> {
}
