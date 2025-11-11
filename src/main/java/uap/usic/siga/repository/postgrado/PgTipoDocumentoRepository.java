package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgTipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgTipoDocumentoRepository extends JpaRepository<PgTipoDocumento, Long> {

    @Query("SELECT e FROM PgTipoDocumento e WHERE e.idEstado = 'A'")
    List<PgTipoDocumento> listarActivos();
}
