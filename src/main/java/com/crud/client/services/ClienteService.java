package com.crud.client.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crud.client.domain.Cliente;
import com.crud.client.repositories.ClienteRepository;
import com.crud.client.resources.dto.ClienteDTO;
import com.crud.client.resources.exception.BadRequestException;
import com.crud.client.resources.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository repository;
	
	public Cliente save(Cliente cliente) {
		cliente.setCpf(cliente.getCpf().replace("-", "").replace(".", ""));	
		return repository.save(cliente);
	}
	
	public Cliente update(Cliente cliente) {
		findById(cliente.getId());		
		return save(cliente);
	}
	
	public boolean hasCpf(String cpf) {
		return repository.findByCpf(cpf.replace("-", "").replace(".", "")).isPresent();
	}
	
	public Cliente insert(Cliente cliente) {
		Cliente c = repository.findById(cliente.getId() == null ? 0 : cliente.getId()).orElse(null);
		if(c != null)
			throw new BadRequestException("Já existe cliente cadastrado com esse Id. Para inserir um novo registro, o Id deve ser nulo");
		return save(cliente);
	}
	
	public Cliente findById(Long id) {
		if(id == null)
			return null;
		
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
	}
	
	public void delete(Long id) {
		Cliente cliente = findById(id);
		repository.delete(cliente);
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Cliente fromDTO(ClienteDTO dto) {
		return Cliente.builder()
				.cpf(dto.getCpf())
				.email(dto.getEmails())
				.enderecos(dto.getEnderecos())
				.nome(dto.getNome())
				.id(dto.getId())
				.telefones(dto.getTelefones())				
				.build();
	}
}
