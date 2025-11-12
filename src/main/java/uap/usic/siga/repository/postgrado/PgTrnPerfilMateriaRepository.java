package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgTrnPerfilMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgTrnPerfilMateriaRepository extends JpaRepository<PgTrnPerfilMateria, Long> {
}
