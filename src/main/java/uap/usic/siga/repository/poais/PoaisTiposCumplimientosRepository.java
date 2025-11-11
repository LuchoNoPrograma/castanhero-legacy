package uap.usic.siga.repository.poais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.poais.PoaisTiposCumplimientos;

@Repository
public interface PoaisTiposCumplimientosRepository extends JpaRepository<PoaisTiposCumplimientos, Long> {
}
