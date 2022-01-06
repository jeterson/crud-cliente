package com.crud.client.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.crud.client.resources.dto.ViaCepDTO;

@Component
@FeignClient(url = "https://viacep.com.br/ws", name="ViaCepApi")
public interface ViaCepFeignClient {

	@GetMapping(path = "/{cep}/json")
	ResponseEntity<ViaCepDTO> getEnderecoByCep(@PathVariable String cep);
}
