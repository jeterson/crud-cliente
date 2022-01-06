package com.crud.client.resources.dto;

import lombok.Data;

@Data
public class ViaCepDTO {

	private String cep;
	private String localidade;
	private String complemento;
	private String bairro;
	private String uf;
	private String logradouro;
		
}
