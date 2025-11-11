package uap.usic.siga.seguridad.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import uap.usic.siga.seguridad.IAuthenticationFacade;

@Component
public class AuthenticationFacadeImpl implements IAuthenticationFacade{

	public AuthenticationFacadeImpl() {
        super();
    }
	
	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	
}
