package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgTipoConvalidacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgTipoConvalidacionRepository extends JpaRepository<PgTipoConvalidacion, Long> {

    @Query("SELECT e FROM PgTipoConvalidacion e WHERE e.idEstado = 'A'")
    List<PgTipoConvalidacion> listarActivos();
}
