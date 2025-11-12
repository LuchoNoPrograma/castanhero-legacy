package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.PrsCiExpedidos;

import java.util.List;

@Repository
public interface PrsCiExpedidosRepository extends JpaRepository<PrsCiExpedidos, Long> {

    @Query("SELECT c FROM PrsCiExpedidos c WHERE c.idEstado = 'A'")
    List<PrsCiExpedidos> findAllActive();

    List<PrsCiExpedidos> findByPrsCiExpedidoSigla(String sigla);
}
