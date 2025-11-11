package uap.usic.siga.seguridad;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {

    Authentication getAuthentication();

}
