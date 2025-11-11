package uap.usic.siga.repository.sicoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.sicoes.ScsPrsContratado;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScsPrsContratadoRepository extends JpaRepository<ScsPrsContratado, Long> {

    List<ScsPrsContratado> findByIdEstadoNot(String idEstado);

    @Query("SELECT c FROM ScsPrsContratado c WHERE c.persona.idPersona = :idPersona AND c.idEstado <> 'X'")
    Optional<ScsPrsContratado> findByPersonaId(@Param("idPersona") Long idPersona);
}
