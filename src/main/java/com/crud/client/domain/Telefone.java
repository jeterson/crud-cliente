package com.crud.client.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.crud.client.enums.TipoTelefone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Telefone {

	@NotBlank
	@NotNull
	private String telefone;	
	@NotNull
	private TipoTelefone tipo;
	
	public void setTelefone(String telefone) {
		this.telefone = telefone.replace("(", "").replace(")", "").replace("-", "").replace("'", "");
	}
}
