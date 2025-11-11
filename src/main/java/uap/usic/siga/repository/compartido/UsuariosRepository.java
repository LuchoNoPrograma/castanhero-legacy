package uap.usic.siga.repository.compartido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.Usuarios;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

    Optional<Usuarios> findByEmail(String email);

    Optional<Usuarios> findByUsername(String username);

    Optional<Usuarios> findByName(String name);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("SELECT u FROM Usuarios u " +
           "LEFT JOIN FETCH u.personas p " +
           "WHERE u.idEstado = 'A' AND p.idEstado = 'A'")
    List<Usuarios> listarUsuariosActivos();

    @Query("SELECT u FROM Usuarios u " +
           "LEFT JOIN FETCH u.personas p " +
           "LEFT JOIN FETCH u.roles r " +
           "WHERE u.idEstado = 'A' AND p.idEstado = 'A'")
    Page<Usuarios> listarUsuariosActivosPaginado(Pageable pageable);

    @Query("SELECT u FROM Usuarios u " +
           "LEFT JOIN FETCH u.roles r " +
           "WHERE u.email = :email AND u.idEstado = 'A'")
    Optional<Usuarios> buscarPorEmailConRoles(@Param("email") String email);

    @Query("SELECT u FROM Usuarios u " +
           "JOIN u.roles r " +
           "WHERE r.name = :nombreRol AND u.idEstado = 'A'")
    List<Usuarios> buscarPorRol(@Param("nombreRol") String nombreRol);

    @Query("SELECT u FROM Usuarios u " +
           "LEFT JOIN u.personas p " +
           "WHERE p.idPersona = :idPersona AND u.idEstado = 'A'")
    Optional<Usuarios> buscarPorIdPersona(@Param("idPersona") Long idPersona);

    @Query("SELECT COUNT(u) FROM Usuarios u WHERE u.idEstado = 'A'")
    long contarUsuariosActivos();
}
