package uap.usic.siga.servicios.userDetails;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uap.usic.siga.entidades.Usuarios;
import uap.usic.siga.servicios.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Keno&Kemo on 18.02.2018..
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios user = userService.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return new UserDetailsImpl(user);
    }
}
