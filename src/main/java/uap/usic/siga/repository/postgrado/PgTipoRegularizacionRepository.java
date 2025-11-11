package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgTipoRegularizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgTipoRegularizacionRepository extends JpaRepository<PgTipoRegularizacion, Long> {

    @Query("SELECT e FROM PgTipoRegularizacion e WHERE e.idEstado = 'A'")
    List<PgTipoRegularizacion> listarActivos();
}
