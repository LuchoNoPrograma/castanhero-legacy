package uap.usic.siga.repository.poais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.poais.Poais;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoaisRepository extends JpaRepository<Poais, Long> {

    @Query("SELECT p FROM Poais p WHERE p.pnlPersonalAdministrativos.idPnlPersonalAdministrativo = :idPersonal AND p.gestion = :gestion")
    List<Poais> findByPersonalAdministrativoAndGestion(
        @Param("idPersonal") Long idPersonal,
        @Param("gestion") Integer gestion
    );

    @Query("SELECT p FROM Poais p WHERE p.pnlPersonalAdministrativos.idPnlPersonalAdministrativo = :idPersonal AND p.gestion = :gestion AND p.estado = :estado")
    Optional<Poais> findByPersonalGestionAndEstado(
        @Param("idPersonal") Long idPersonal,
        @Param("gestion") Integer gestion,
        @Param("estado") String estado
    );

    @Query("SELECT p FROM Poais p WHERE p.pnlPersonalAdministrativos.idPnlPersonalAdministrativo = :idPersonal")
    List<Poais> findAllByPersonalAdministrativo(@Param("idPersonal") Long idPersonal);
}
