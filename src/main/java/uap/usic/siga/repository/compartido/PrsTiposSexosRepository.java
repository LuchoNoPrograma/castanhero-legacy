package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.PrsTiposSexos;

import java.util.List;

@Repository
public interface PrsTiposSexosRepository extends JpaRepository<PrsTiposSexos, Long> {

    @Query("SELECT t FROM PrsTiposSexos t WHERE t.idEstado = 'A'")
    List<PrsTiposSexos> findAllActive();
}
