package uap.usic.siga.repository.poais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.poais.PoaisRequisitos;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoaisRequisitosRepository extends JpaRepository<PoaisRequisitos, Long> {

    List<PoaisRequisitos> findByPoais_IdPoai(Long idPoai);

    Optional<PoaisRequisitos> findFirstByPoais_IdPoai(Long idPoai);
}
