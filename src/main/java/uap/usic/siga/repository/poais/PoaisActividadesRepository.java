package uap.usic.siga.repository.poais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.poais.PoaisActividades;

import java.util.List;

@Repository
public interface PoaisActividadesRepository extends JpaRepository<PoaisActividades, Long> {

    List<PoaisActividades> findByPoaisResultados_IdResultado(Long idResultado);
}
