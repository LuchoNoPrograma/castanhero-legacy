package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgAdmTipoAdmision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgAdmTipoAdmisionRepository extends JpaRepository<PgAdmTipoAdmision, Long> {

    @Query("SELECT e FROM PgAdmTipoAdmision e WHERE e.idEstado = 'A'")
    List<PgAdmTipoAdmision> listarActivos();
}
