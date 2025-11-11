package uap.usic.siga.repository.sicoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.sicoes.ScsModalidad;

import java.util.List;

@Repository
public interface ScsModalidadRepository extends JpaRepository<ScsModalidad, Long> {

    List<ScsModalidad> findByIdEstadoNot(String idEstado);
}
