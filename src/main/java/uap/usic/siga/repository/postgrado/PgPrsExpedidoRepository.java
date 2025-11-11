package uap.usic.siga.repository.postgrado;

import uap.usic.siga.domain.postgrado.PgPrsExpedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgPrsExpedidoRepository extends JpaRepository<PgPrsExpedido, Long> {

    @Query("SELECT e FROM PgPrsExpedido e WHERE e.idEstado = 'A'")
    List<PgPrsExpedido> listarActivos();
}
