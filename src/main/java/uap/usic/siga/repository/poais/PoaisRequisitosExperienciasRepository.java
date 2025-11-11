package uap.usic.siga.repository.poais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.poais.PoaisRequisitosExperiencias;

import java.util.List;

@Repository
public interface PoaisRequisitosExperienciasRepository extends JpaRepository<PoaisRequisitosExperiencias, Long> {

    List<PoaisRequisitosExperiencias> findByPoaisRequisitos_IdRequisito(Long idRequisito);
}
