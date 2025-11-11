package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgInsTipoEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgInsTipoEstudianteRepository extends JpaRepository<PgInsTipoEstudiante, Long> {
}
