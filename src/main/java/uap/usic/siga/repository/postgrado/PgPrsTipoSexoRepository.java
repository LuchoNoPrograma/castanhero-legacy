package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrsTipoSexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgPrsTipoSexoRepository extends JpaRepository<PgPrsTipoSexo, Long> {

    @Query("SELECT e FROM PgPrsTipoSexo e WHERE e.idEstado = 'A'")
    List<PgPrsTipoSexo> listarActivos();
}
