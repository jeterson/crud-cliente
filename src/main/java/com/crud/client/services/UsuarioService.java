package com.crud.client.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.client.domain.Usuario;
import com.crud.client.repositories.UsuarioRepository;
import com.crud.client.security.UserSecurity;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Usuario save(Usuario usuario) {
		String pwd = bCryptPasswordEncoder.encode(usuario.getPassword());
		usuario.setPassword(pwd);
		return repository.save(usuario);
	}

	public static UserSecurity authenticated() {
		try {
			return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		}catch (Exception e) {
			return null;
		}
		
	}
}
