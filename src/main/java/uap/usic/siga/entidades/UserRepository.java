package uap.usic.siga.entidades;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Keno&Kemo on 30.09.2017..
 */

@Repository
public interface UserRepository extends JpaRepository<Usuarios, Long> {

    Usuarios findByUsername(String username);

   Usuarios findByEmailAndIdNot(String email, Long id);

    Usuarios findByUsernameAndIdNot(String username, Long id);

    Page<Usuarios> findByUsernameContainingOrderByIdAsc(String username, Pageable pageable);

    Page<Usuarios> findByEmailContainingOrderByIdAsc(String email, Pageable pageable);

    Page<Usuarios> findByNameContainingOrderByIdAsc(String name, Pageable pageable);

    Page<Usuarios> findBySurnameContainingOrderByIdAsc(String surname, Pageable pageable);

    //region Find eagerly
    //==========================================================================
    @Query("SELECT u FROM Usuarios u JOIN FETCH u.roles")
    List<Usuarios> findAllEagerly();

    @Query("SELECT u FROM Usuarios u JOIN FETCH u.roles WHERE u.email = (:email)")
    Usuarios findByEmailEagerly(@Param("email") String email);

    @Query("SELECT u FROM Usuarios u JOIN FETCH u.roles WHERE u.id = (:id)")
    Usuarios findByIdEagerly(@Param("id") Long id);
    //==========================================================================
    //endregion


}
