package uap.usic.siga.repository.sicoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.sicoes.ScsBoletaRespaldatoria;

import java.util.List;

@Repository
public interface ScsBoletaRespaldatoriaRepository extends JpaRepository<ScsBoletaRespaldatoria, Long> {

    List<ScsBoletaRespaldatoria> findByIdEstadoNot(String idEstado);
}
