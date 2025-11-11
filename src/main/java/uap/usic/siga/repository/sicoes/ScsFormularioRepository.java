package uap.usic.siga.repository.sicoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.sicoes.ScsFormulario;

import java.util.List;

@Repository
public interface ScsFormularioRepository extends JpaRepository<ScsFormulario, Long> {

    List<ScsFormulario> findByIdEstadoNot(String idEstado);
}
