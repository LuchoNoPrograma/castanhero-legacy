package uap.usic.siga.repository.compartido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uap.usic.siga.domain.compartido.Personas;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonasRepository extends JpaRepository<Personas, Long> {

    Optional<Personas> findByCi(String ci);

    Optional<Personas> findByEmail(String email);

    boolean existsByCi(String ci);

    boolean existsByEmail(String email);

    @Query("SELECT p FROM Personas p WHERE p.idEstado = 'A'")
    List<Personas> listarPersonasActivas();

    @Query("SELECT p FROM Personas p WHERE p.idEstado = 'A'")
    Page<Personas> listarPersonasActivasPaginado(Pageable pageable);

    @Query("SELECT p FROM Personas p " +
           "WHERE p.idEstado = 'A' " +
           "AND (LOWER(p.nombres) LIKE LOWER(CONCAT('%', :busqueda, '%')) " +
           "OR LOWER(p.paterno) LIKE LOWER(CONCAT('%', :busqueda, '%')) " +
           "OR LOWER(p.materno) LIKE LOWER(CONCAT('%', :busqueda, '%')) " +
           "OR p.ci LIKE CONCAT('%', :busqueda, '%'))")
    Page<Personas> buscarPersonas(@Param("busqueda") String busqueda, Pageable pageable);

    @Query("SELECT p FROM Personas p " +
           "WHERE p.ci = :ci AND p.idEstado = 'A'")
    Optional<Personas> buscarPorCiActiva(@Param("ci") String ci);

    @Query("SELECT COUNT(p) FROM Personas p WHERE p.idEstado = 'A'")
    long contarPersonasActivas();
}
