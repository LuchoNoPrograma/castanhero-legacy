package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.MnuTiposFunciones;

import java.util.List;

@Repository
public interface MnuTiposFuncionesRepository extends JpaRepository<MnuTiposFunciones, Long> {

    @Query("SELECT t FROM MnuTiposFunciones t WHERE t.idEstado = 'A'")
    List<MnuTiposFunciones> findAllActive();

    List<MnuTiposFunciones> findByTipoFuncionContainingIgnoreCase(String tipoFuncion);
}
