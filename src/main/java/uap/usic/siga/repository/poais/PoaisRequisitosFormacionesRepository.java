package uap.usic.siga.repository.poais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.poais.PoaisRequisitosFormaciones;

import java.util.List;

@Repository
public interface PoaisRequisitosFormacionesRepository extends JpaRepository<PoaisRequisitosFormaciones, Long> {

    List<PoaisRequisitosFormaciones> findByPoaisRequisitos_IdRequisito(Long idRequisito);
}
