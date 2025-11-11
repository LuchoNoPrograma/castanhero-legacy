package uap.usic.siga.servicios.userDetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import uap.usic.siga.entidades.Roles;
import uap.usic.siga.entidades.Usuarios;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */


public class UserDetailsImpl implements UserDetails {
    private Usuarios user;
    public UserDetailsImpl(Usuarios user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        List<Roles> roles = user.getRoles();
        for( Roles role : roles ) {
            authorities.add( new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
    
    public Long getId() {
        return user.getId();
    }

    
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
