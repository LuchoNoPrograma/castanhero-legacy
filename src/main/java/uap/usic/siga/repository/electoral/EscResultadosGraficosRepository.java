package uap.usic.siga.repository.electoral;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.electoral.EscResultadosGraficos;

@Repository
public interface EscResultadosGraficosRepository extends JpaRepository<EscResultadosGraficos, Long> {
}
