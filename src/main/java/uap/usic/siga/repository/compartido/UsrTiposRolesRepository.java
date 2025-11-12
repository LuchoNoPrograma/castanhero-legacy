package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.UsrTiposRoles;

import java.util.List;

@Repository
public interface UsrTiposRolesRepository extends JpaRepository<UsrTiposRoles, Integer> {

    @Query("SELECT t FROM UsrTiposRoles t WHERE t.idEstado = 'A'")
    List<UsrTiposRoles> findAllActive();

    List<UsrTiposRoles> findByTipoRolContainingIgnoreCase(String tipoRol);
}
