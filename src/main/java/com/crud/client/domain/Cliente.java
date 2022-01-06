package com.crud.client.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({AuditingEntityListener.class})
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Column(unique = true)
	private String cpf;
	
	@CreatedDate
	private LocalDate createdDate;
	
	@LastModifiedDate
	private LocalDate updatedDate;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedBy
	private String updatedBy;
	
	@Builder.Default
	@ElementCollection(fetch = FetchType.LAZY)	
	private Set<String> email = new HashSet<>();
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name="cliente_endereco", joinColumns = @JoinColumn(name="cliente_id"))
	@AttributeOverrides({
		@AttributeOverride(name = "cep", column = @Column(name="cep")),
		@AttributeOverride(name = "complemento", column = @Column(name="complemento")),
		@AttributeOverride(name = "logradouro", column = @Column(name="logradouro")),
		@AttributeOverride(name = "bairro", column = @Column(name="bairro")),
		@AttributeOverride(name = "cidade", column = @Column(name="cidade")),
		@AttributeOverride(name = "uf", column = @Column(name="uf"))
	})
	@Builder.Default
	private Set<Endereco> enderecos = new  HashSet<>();
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name="cliente_telefone")
	@AttributeOverrides({
		@AttributeOverride(name="tipo", column=@Column(name="tipo"))
	})
	@Builder.Default
	private Set<Telefone> telefones = new HashSet<>();
}
