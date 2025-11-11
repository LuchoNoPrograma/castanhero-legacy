package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgArchivoAdjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgArchivoAdjuntoRepository extends JpaRepository<PgArchivoAdjunto, Long> {

    @Query("SELECT e FROM PgArchivoAdjunto e WHERE e.idEstado = 'A'")
    List<PgArchivoAdjunto> listarActivos();
}
