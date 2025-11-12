package uap.usic.siga.servicios;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uap.usic.siga.dto.UserDto;
import uap.usic.siga.dto.UserUpdateDto;
import uap.usic.siga.entidades.Roles;

import uap.usic.siga.entidades.UserRepository;
import uap.usic.siga.entidades.Usuarios;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.Collections.emptyList;

/**
 * Created by Keno&Kemo on 18.10.2017..
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CacheManager cacheManager;

    //region find methods
    //==============================================================================================
    @Cacheable(value = "cache.allUsers")
    public List<Usuarios> findAll() {
        return userRepository.findAll();
    }

    @Cacheable(value = "cache.allUsersPageable")
    public Page<Usuarios> findAllPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Cacheable(value = "cache.userByEmail", key = "#email", unless = "#result == null")
    public Usuarios findByEmail(String email) {
        return userRepository.findByEmailEagerly(email);
    }

    @Cacheable(value = "cache.userById", key = "#id", unless = "#result == null")
    public Optional<Usuarios> findById(Long id) {
        return userRepository.findById(id);
    }

    public Page<Usuarios> findByIdPageable(Long id, Pageable pageRequest) {
        Optional<Usuarios> user = userRepository.findById(id);
        List<Usuarios> users = user.map(Collections::singletonList).orElse(emptyList());
        return new PageImpl<>(users, pageRequest, users.size());
    }

    public Usuarios findByEmailAndIdNot(String email, Long id) {
        return userRepository.findByEmailAndIdNot(email, id);
    }

    public Usuarios findByUsernameAndIdNot(String username, Long id) {
        return userRepository.findByUsernameAndIdNot(username, id);
    }

    public Usuarios findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //region Find eagerly
    public Usuarios findByIdEagerly(Long id) {
        return userRepository.findByIdEagerly(id);
    }

    @Cacheable(value = "cache.allUsersEagerly")
    public List<Usuarios> findAllEagerly() {
        return userRepository.findAllEagerly();
    }
    //endregion

    //region Find by containing
    @Cacheable(value = "cache.byNameContaining")
    public Page<Usuarios> findByNameContaining(String name, Pageable pageable) {
        return userRepository.findByNameContainingOrderByIdAsc(name, pageable);
    }

    @Cacheable(value = "cache.bySurnameContaining")
    public Page<Usuarios> findBySurnameContaining(String surname, Pageable pageable) {
        return userRepository.findBySurnameContainingOrderByIdAsc(surname, pageable);
    }

    @Cacheable(value = "cache.byUsernameContaining")
    public Page<Usuarios> findByUsernameContaining(String username, Pageable pageable) {
        return userRepository.findByUsernameContainingOrderByIdAsc(username, pageable);
    }

    @Cacheable(value = "cache.byEmailContaining")
    public Page<Usuarios> findByEmailContaining(String email, Pageable pageable) {
        return userRepository.findByEmailContainingOrderByIdAsc(email, pageable);
    }
    //endregion

    //==============================================================================================
    //endregion


    @Transactional
    @CacheEvict(value = {"cache.allUsersPageable",
            "cache.allUsers",
            "cache.userByEmail",
            "cache.userById",
            "cache.allUsersEagerly",
            "cache.byNameContaining",
            "cache.bySurnameContaining",
            "cache.byUsernameContaining",
            "cache.byEmailContaining"},
            allEntries = true)
    public void save(Usuarios user) {
        userRepository.save(user);
    }

    @CacheEvict(value = {"cache.allUsersPageable",
            "cache.allUsers",
            "cache.userByEmail",
            "cache.userById",
            "cache.allUsersEagerly",
            "cache.byNameContaining",
            "cache.bySurnameContaining",
            "cache.byUsernameContaining",
            "cache.byEmailContaining"},
            allEntries = true)
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Usuarios createNewAccount(UserDto userDto) {
        Usuarios user = new Usuarios();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singletonList(roleService.findByName("ROLE_USER")));
        user.setPersonas(userDto.getPersonas());
        return user;
    }

    public Usuarios getUpdatedUser(Usuarios persistedUser, UserUpdateDto userUpdateDto) {
        persistedUser.setName(userUpdateDto.getName());
        persistedUser.setSurname(userUpdateDto.getSurname());
        persistedUser.setUsername(userUpdateDto.getUsername());
        persistedUser.setEmail(userUpdateDto.getEmail());
        persistedUser.setRoles(getAssignedRolesList(userUpdateDto));
        persistedUser.setEnabled(userUpdateDto.isEnabled());
        return persistedUser;
    }

    public List<Roles> getAssignedRolesList(UserUpdateDto userUpdateDto) {
        Map<Long, Roles> assignedRoleMap = new HashMap<>();
        List<Roles> roles = userUpdateDto.getRoles();
        for (Roles role : roles) assignedRoleMap.put(role.getId(), role);

        List<Roles> userRoles = new ArrayList<>();
        List<Roles> allRoles = roleService.findAll();

        for (Roles role : allRoles) {
            if (assignedRoleMap.containsKey(role.getId())) userRoles.add(role);
            else userRoles.add(null);
        }
        return userRoles;
    }
}
