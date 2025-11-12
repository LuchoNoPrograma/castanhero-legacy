package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.FclEstamentos;

import java.util.List;

@Repository
public interface FclEstamentosRepository extends JpaRepository<FclEstamentos, Long> {

    @Query("SELECT e FROM FclEstamentos e WHERE e.idEstado = 'A'")
    List<FclEstamentos> findAllActive();

    List<FclEstamentos> findByEstamentoContainingIgnoreCase(String estamento);
}
