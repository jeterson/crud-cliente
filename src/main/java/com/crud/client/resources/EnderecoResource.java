package com.crud.client.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.client.domain.Endereco;
import com.crud.client.feignclients.ViaCepFeignClient;
import com.crud.client.resources.dto.ViaCepDTO;

@RestController
@RequestMapping(path = "enderecos")
public class EnderecoResource {

	@Autowired
	private ViaCepFeignClient viaCepFeignClient;
	
	@GetMapping(path = "/viacep/{cep}")
	public ResponseEntity<Endereco> getEnderecoByViaCep(@PathVariable String cep) {
		ResponseEntity<ViaCepDTO> responseEntityDto = viaCepFeignClient.getEnderecoByCep(cep);
		ViaCepDTO dto = responseEntityDto.getBody();		
		return ResponseEntity.ok(Endereco.of(dto));
	}
}
