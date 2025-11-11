package uap.usic.siga.repository.sicoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.sicoes.ScsArchivoAdjunto;

@Repository
public interface ScsArchivoAdjuntoRepository extends JpaRepository<ScsArchivoAdjunto, Long> {
}
