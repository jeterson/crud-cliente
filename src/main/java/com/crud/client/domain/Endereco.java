package com.crud.client.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.crud.client.resources.dto.ViaCepDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

	@NotBlank
	@NotNull
	private String cep;
	private String complemento;
	@NotBlank
	@NotNull
	private String logradouro;
	@NotBlank
	@NotNull
	private String bairro;
	@NotBlank
	@NotNull
	private String cidade;
	@NotBlank
	@NotNull
	private String uf;
	
	public String getCep() {
		return cep.replace("-", "");
	}
	
	
	public static Endereco of(ViaCepDTO dto) {
		return Endereco.builder()
				.cep(dto.getCep())
				.bairro(dto.getBairro())
				.uf(dto.getUf())
				.cidade(dto.getLocalidade())
				.complemento(dto.getComplemento())				
				.build();
	}
}
