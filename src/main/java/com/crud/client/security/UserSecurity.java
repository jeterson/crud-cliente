package com.crud.client.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.crud.client.enums.Role;

public class UserSecurity implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String password;
	 private String username;
	 private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return authorities;
	}
	
	
	public UserSecurity(Integer id, String password, String username, Set<Role> roles) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
		this.authorities = roles.stream().map(x -> new SimpleGrantedAuthority(x.getRoleName())).collect(Collectors.toSet());
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	public Integer getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
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
		return true;
	}
	
	public boolean hasRole(Role role) {
		return getAuthorities().contains(new SimpleGrantedAuthority(role.getRoleName()));
	}

}
