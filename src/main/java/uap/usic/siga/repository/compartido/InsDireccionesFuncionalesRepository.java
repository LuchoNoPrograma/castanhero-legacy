package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.InsDireccionesFuncionales;

import java.util.List;

@Repository
public interface InsDireccionesFuncionalesRepository extends JpaRepository<InsDireccionesFuncionales, Long> {

    @Query("SELECT d FROM InsDireccionesFuncionales d WHERE d.idEstado = 'A'")
    List<InsDireccionesFuncionales> findAllActive();

    List<InsDireccionesFuncionales> findByDireccionFuncionalContainingIgnoreCase(String direccionFuncional);
}
