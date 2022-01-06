package com.crud.client.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.client.domain.Cliente;
import com.crud.client.resources.dto.ClienteDTO;
import com.crud.client.services.ClienteService;

@RestController
@RequestMapping(path="clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping	
	public ResponseEntity<ClienteDTO> postCliente(@Valid @RequestBody ClienteDTO dto) {	
		Cliente cliente = service.fromDTO(dto);
		cliente = service.insert(cliente);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ClienteDTO.of(cliente));
	}
	@PreAuthorize("hasAuthority('ROLE_COMMON') || hasAuthority('ROLE_ADMIN')")	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ClienteDTO> getCliente(@PathVariable Long id) {
		Cliente cliente  = service.findById(id);
		return ResponseEntity.ok(ClienteDTO.of(cliente));
	}
	
	@PreAuthorize("hasAuthority('ROLE_COMMON') || hasAuthority('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> getClienteAll() {
		List<Cliente> clientes  = service.findAll();
		List<ClienteDTO> clientesDto = clientes.stream().map(c -> ClienteDTO.of(c)).collect(Collectors.toList());
		return ResponseEntity.ok(clientesDto);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ClienteDTO> deleteCliente(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PutMapping(path="/{id}")
	public ResponseEntity<ClienteDTO> putCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {	
		dto.setId(id);
		Cliente cliente = service.fromDTO(dto);
		cliente = service.update(cliente);		
		return ResponseEntity.status(HttpStatus.CREATED).body(ClienteDTO.of(cliente));
	}
}
