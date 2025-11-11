package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgFacultad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgFacultadRepository extends JpaRepository<PgFacultad, Long> {

    @Query("SELECT e FROM PgFacultad e WHERE e.idEstado = 'A'")
    List<PgFacultad> listarActivos();
}
