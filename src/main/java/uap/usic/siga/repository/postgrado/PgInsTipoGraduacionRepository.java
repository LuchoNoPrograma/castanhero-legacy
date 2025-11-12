package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgInsTipoGraduacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgInsTipoGraduacionRepository extends JpaRepository<PgInsTipoGraduacion, Long> {
}
