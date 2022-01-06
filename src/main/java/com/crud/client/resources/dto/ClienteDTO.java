package com.crud.client.resources.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.crud.client.domain.Cliente;
import com.crud.client.domain.Endereco;
import com.crud.client.domain.Telefone;
import com.crud.client.validators.UniqueCPF;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ClienteDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull
	@NotBlank
	@Size(max = 100, min=3)	
	@Pattern(regexp = "^[A-Za-z0-9 ]*[A-Za-z0-9][A-Za-z0-9 ]*$", message = "deve conter apenas letras, numeros e/ou espaços")
	private String nome;
	
	@NotNull
	@NotBlank
	@CPF
	
	private String cpf;
	
	@Valid
	@Builder.Default
	private Set<Endereco> enderecos = new HashSet<Endereco>();
	@NotEmpty
	@Valid
	@Builder.Default
	private Set<Telefone> telefones = new HashSet<Telefone>();
	@NotEmpty
	@Valid
	@Builder.Default
	private Set<@NotBlank @NotNull @Email String> emails = new HashSet<String>();
		
	
	public static ClienteDTO of(Cliente cliente) {
		return ClienteDTO.builder()
				.cpf(cliente.getCpf())
				.telefones(cliente.getTelefones())
				.emails(cliente.getEmail())
				.enderecos(cliente.getEnderecos())
				.nome(cliente.getNome())
				.id(cliente.getId())
				.build();
	}
	
	
}
