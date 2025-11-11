package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgGradoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgGradoAcademicoRepository extends JpaRepository<PgGradoAcademico, Long> {

    @Query("SELECT e FROM PgGradoAcademico e WHERE e.idEstado = 'A'")
    List<PgGradoAcademico> listarActivos();
}
