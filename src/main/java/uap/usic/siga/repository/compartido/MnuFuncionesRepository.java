package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.MnuFunciones;

import java.util.List;

@Repository
public interface MnuFuncionesRepository extends JpaRepository<MnuFunciones, Long> {

    @Query("SELECT f FROM MnuFunciones f WHERE f.idEstado = 'A'")
    List<MnuFunciones> findAllActive();

    @Query("SELECT f FROM MnuFunciones f WHERE f.personas.id = :idPersona AND f.idEstado = 'A'")
    List<MnuFunciones> findByPersonaId(@Param("idPersona") Long idPersona);
}
