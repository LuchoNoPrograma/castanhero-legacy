package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgEjecucionModuloEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgEjecucionModuloEstudianteRepository extends JpaRepository<PgEjecucionModuloEstudiante, Long> {

    @Query("SELECT e FROM PgEjecucionModuloEstudiante e WHERE e.idEstado = 'A'")
    List<PgEjecucionModuloEstudiante> listarActivos();
}
