package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgTipoCalificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgTipoCalificacionRepository extends JpaRepository<PgTipoCalificacion, Long> {

    @Query("SELECT e FROM PgTipoCalificacion e WHERE e.idEstado = 'A'")
    List<PgTipoCalificacion> listarActivos();
}
