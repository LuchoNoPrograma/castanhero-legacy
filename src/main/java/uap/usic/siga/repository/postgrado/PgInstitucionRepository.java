package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgInstitucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgInstitucionRepository extends JpaRepository<PgInstitucion, Long> {

    @Query("SELECT e FROM PgInstitucion e WHERE e.idEstado = 'A'")
    List<PgInstitucion> listarActivos();
}
