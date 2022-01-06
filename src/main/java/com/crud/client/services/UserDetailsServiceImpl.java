package com.crud.client.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crud.client.domain.Usuario;
import com.crud.client.repositories.UsuarioRepository;
import com.crud.client.security.UserSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> op =  repository.findByUsername(username);
		if(!op.isPresent())
			throw new UsernameNotFoundException(username);
		
		Usuario usuario = op.get();
		return new UserSecurity(usuario.getId().intValue(), usuario.getPassword(), username, usuario.getRoles());
	}

	
}
