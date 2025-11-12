package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgMtrMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgMtrMateriaRepository extends JpaRepository<PgMtrMateria, Long> {
}
