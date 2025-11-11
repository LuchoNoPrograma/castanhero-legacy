package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrgPlnGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgPrgPlnGrupoRepository extends JpaRepository<PgPrgPlnGrupo, Long> {

    @Query("SELECT e FROM PgPrgPlnGrupo e WHERE e.idEstado = 'A'")
    List<PgPrgPlnGrupo> listarActivos();
}
