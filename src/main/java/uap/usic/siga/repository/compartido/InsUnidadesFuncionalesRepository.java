package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.InsUnidadesFuncionales;

import java.util.List;

@Repository
public interface InsUnidadesFuncionalesRepository extends JpaRepository<InsUnidadesFuncionales, Long> {

    @Query("SELECT u FROM InsUnidadesFuncionales u WHERE u.idEstado = 'A'")
    List<InsUnidadesFuncionales> findAllActive();

    List<InsUnidadesFuncionales> findByUnidadFuncionalContainingIgnoreCase(String unidadFuncional);
}
