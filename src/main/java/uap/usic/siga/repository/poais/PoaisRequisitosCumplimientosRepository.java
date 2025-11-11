package uap.usic.siga.repository.poais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.poais.PoaisRequisitosCumplimientos;

import java.util.List;

@Repository
public interface PoaisRequisitosCumplimientosRepository extends JpaRepository<PoaisRequisitosCumplimientos, Long> {

    List<PoaisRequisitosCumplimientos> findByPoaisRequisitos_IdRequisito(Long idRequisito);
}
