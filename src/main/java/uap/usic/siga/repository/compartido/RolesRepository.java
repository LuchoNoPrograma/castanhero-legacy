package uap.usic.siga.repository.compartido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.Roles;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByName(String name);

    boolean existsByName(String name);

    @Query("SELECT r FROM Roles r ORDER BY r.name ASC")
    List<Roles> listarRolesOrdenados();

    @Query("SELECT COUNT(r) FROM Roles r")
    long contarRoles();
}
