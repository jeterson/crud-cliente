package com.crud.client.enums;

import com.crud.client.resources.exception.NotFoundException;

public enum Role {
	ADMIN(1, "ROLE_ADMIN"), COMMON(2, "ROLE_COMMON");
	
	private int cod;
	private String roleName;
	private Role(int cod, String roleName) {
		this.cod = cod;
		this.roleName = roleName;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public static Role of(Integer cod) {
		if(cod == null)
			return null;
		
		for(Role x : Role.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new NotFoundException("Role Id inválido: " + cod);
	}
}
