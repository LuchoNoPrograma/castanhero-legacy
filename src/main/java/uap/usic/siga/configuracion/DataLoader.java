package uap.usic.siga.configuracion;

import uap.usic.siga.entidades.Roles;

import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.RoleService;
import uap.usic.siga.servicios.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    public DataLoader(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) return;

        Roles roleAdmin = createRoleIfNotFound("ROLE_ADMIN");
        Roles roleUser = createRoleIfNotFound("ROLE_USER");
        List<Roles> adminRoles = Collections.singletonList(roleAdmin);
        List<Roles> userRoles = Collections.singletonList(roleUser);

        createUserIfNotFound("admin@gmail.com", "Admin", "Admin", "admin", "admin", adminRoles);

        for (int i = 1; i < 2; i++)
            createUserIfNotFound("user" + i + "@gmail.com", "User" + i, "User" + i, "user" + i, "user" + i, userRoles);
        alreadySetup = true;
    }

    @Transactional
    Roles createRoleIfNotFound(final String name) {
        Roles role = roleService.findByName(name);
        if (role == null) {
            role = new Roles(name);
            roleService.save(role);
        }
        return role;
    }

    @Transactional
    void createUserIfNotFound(final String email,
                              final String name,
                              final String surname,
                              final String username,
                              final String password,
                              final List<Roles> userRoles) {
        Usuarios user = userService.findByEmail(email);
        if (user == null) {
            user = new Usuarios();
            user.setName(name);
            user.setSurname(surname);
            user.setUsername(username);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setEmail(email);
            user.setRoles(userRoles);
            user.setEnabled(true);
            userService.save(user);
        }
    }
}