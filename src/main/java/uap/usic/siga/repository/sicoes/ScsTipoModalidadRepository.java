package uap.usic.siga.repository.sicoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.sicoes.ScsTipoModalidad;

import java.util.List;

@Repository
public interface ScsTipoModalidadRepository extends JpaRepository<ScsTipoModalidad, Long> {

    List<ScsTipoModalidad> findByIdEstadoNot(String idEstado);
}
