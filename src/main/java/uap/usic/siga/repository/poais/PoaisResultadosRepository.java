package uap.usic.siga.repository.poais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.poais.PoaisResultados;

import java.util.List;

@Repository
public interface PoaisResultadosRepository extends JpaRepository<PoaisResultados, Long> {

    List<PoaisResultados> findByPoaisObjetivos_IdObjetivo(Long idObjetivo);
}
