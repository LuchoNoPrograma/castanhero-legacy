package uap.usic.siga.repository.personal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.personal.PnlPersonalAdministrativos;

import java.util.Optional;

@Repository
public interface PnlPersonalAdministrativosRepository extends JpaRepository<PnlPersonalAdministrativos, Long> {

    @Query("SELECT p FROM PnlPersonalAdministrativos p WHERE p.personas.idPersona = :idPersona AND p.gestion = :gestion AND p.periodo = :periodo")
    Optional<PnlPersonalAdministrativos> findByPersonaAndGestionAndPeriodo(
        @Param("idPersona") Long idPersona,
        @Param("gestion") Integer gestion,
        @Param("periodo") Integer periodo
    );

    @Query("SELECT p.pnlCargos FROM PnlPersonalAdministrativos p WHERE p.personas.idPersona = :idPersona")
    Optional<PnlPersonalAdministrativos> findCargoByPersonaId(@Param("idPersona") Long idPersona);
}
