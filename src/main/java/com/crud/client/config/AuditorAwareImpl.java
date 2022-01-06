package com.crud.client.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.crud.client.security.UserSecurity;

public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
		      return null;
		    }
		 String usuario = ((UserSecurity) authentication.getPrincipal()).getUsername();
		 return Optional.ofNullable(usuario);
	}

}
