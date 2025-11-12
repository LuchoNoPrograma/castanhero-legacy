package uap.usic.siga.servicios;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import uap.usic.siga.entidades.Roles;
import uap.usic.siga.entidades.RoleRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    //region Find methods
    //==================================================================================
    @Cacheable("cache.allRoles")
    public List<Roles> findAll() {
        return roleRepository.findAll();
    }

    @Cacheable(value = "cache.roleByName", key = "#name", unless = "#result == null")
    public Roles findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Cacheable(value = "cache.roleById", key = "#id", unless = "#result == null")
    public Optional<Roles> findById(Long id) {
        return roleRepository.findById(id);
    }
    //==================================================================================
    //endregion

    @CacheEvict(value = {"cache.allRoles" , "cache.roleByName", "cache.roleById"}, allEntries = true)
    public void save(Roles role) {
        roleRepository.save(role);
    }

    public boolean checkIfRoleNameIsTaken(List<Roles> allRoles, Roles role, Roles persistedRole) {
        boolean roleNameAlreadyExists = false;
        for (Roles role1 : allRoles) {
            //Check if the role name is edited and if it is taken
            if (!role.getName().equals(persistedRole.getName())
                    && role.getName().equals(role1.getName())) {
                roleNameAlreadyExists = true;
            }
        }
        return roleNameAlreadyExists;
    }


}
