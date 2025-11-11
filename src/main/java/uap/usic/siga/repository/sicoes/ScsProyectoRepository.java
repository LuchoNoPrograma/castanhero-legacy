package uap.usic.siga.repository.sicoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.sicoes.ScsProyecto;

import java.util.List;

@Repository
public interface ScsProyectoRepository extends JpaRepository<ScsProyecto, Long> {

    List<ScsProyecto> findByIdEstadoNot(String idEstado);

    List<ScsProyecto> findByGestionAndIdEstadoNot(Integer gestion, String idEstado);

    @Query("SELECT p FROM ScsProyecto p WHERE p.unidadFuncional.idUnidadFuncional = :idUnidadFuncional AND p.idEstado <> 'X'")
    List<ScsProyecto> findByUnidadFuncional(@Param("idUnidadFuncional") Long idUnidadFuncional);
}
