package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.UsrIps;

import java.util.List;

@Repository
public interface UsrIpsRepository extends JpaRepository<UsrIps, Long> {

    @Query("SELECT i FROM UsrIps i WHERE i.idEstado = 'A'")
    List<UsrIps> findAllActive();

    @Query("SELECT i FROM UsrIps i WHERE i.usuarios.id = :idUsuario AND i.idEstado = 'A'")
    List<UsrIps> findByUsuarioId(@Param("idUsuario") Long idUsuario);

    List<UsrIps> findByIp(String ip);
}
