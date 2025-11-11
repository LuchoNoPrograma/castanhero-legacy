package uap.usic.siga.repository.sicoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.sicoes.ScsTipoContrato;

import java.util.List;

@Repository
public interface ScsTipoContratoRepository extends JpaRepository<ScsTipoContrato, Long> {

    List<ScsTipoContrato> findByIdEstadoNot(String idEstado);
}
