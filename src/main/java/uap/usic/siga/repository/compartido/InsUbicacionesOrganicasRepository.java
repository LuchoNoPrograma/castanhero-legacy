package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.InsUbicacionesOrganicas;

import java.util.List;

@Repository
public interface InsUbicacionesOrganicasRepository extends JpaRepository<InsUbicacionesOrganicas, Long> {

    @Query("SELECT u FROM InsUbicacionesOrganicas u WHERE u.idEstado = 'A'")
    List<InsUbicacionesOrganicas> findAllActive();
}
