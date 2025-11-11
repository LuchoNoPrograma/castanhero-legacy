package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrsGradoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgPrsGradoAcademicoRepository extends JpaRepository<PgPrsGradoAcademico, Long> {

    @Query("SELECT e FROM PgPrsGradoAcademico e WHERE e.idEstado = 'A'")
    List<PgPrsGradoAcademico> listarActivos();
}
