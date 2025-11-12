package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPstUniversidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgPstUniversidadRepository extends JpaRepository<PgPstUniversidad, Long> {
}
