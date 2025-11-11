package uap.usic.siga.entidades;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);
}
