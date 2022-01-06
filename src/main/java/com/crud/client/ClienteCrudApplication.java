package com.crud.client;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.crud.client.domain.Cliente;
import com.crud.client.domain.Endereco;
import com.crud.client.domain.Telefone;
import com.crud.client.domain.Usuario;
import com.crud.client.enums.Role;
import com.crud.client.enums.TipoTelefone;
import com.crud.client.services.ClienteService;
import com.crud.client.services.UsuarioService;

@SpringBootApplication
@EnableFeignClients
public class ClienteCrudApplication implements CommandLineRunner {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public static void main(String[] args) {
		SpringApplication.run(ClienteCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		clienteService.save(
				Cliente.builder()
				.cpf("01029184224")
				.nome("Jeterson Miranda Gomes")
				.email(Arrays.asList("jetersonsi@gmail.com").stream().collect(Collectors.toSet()))
				.telefones(Arrays.asList(Telefone.builder()
											.telefone("69999118936")
											.tipo(TipoTelefone.CELULAR)
											.build()).stream().collect(Collectors.toSet()))
				.enderecos(Arrays.asList(Endereco.builder()
											.bairro("Nova Esperanca")
											.cidade("Cacoal")
											.uf("RO")
											.complemento("Condominio")
											.cep("76970000")
											.logradouro("Rua PRo Maria Lucia")											
											.build()).stream().collect(Collectors.toSet()))
				.build()
				);
		
		Usuario usuarioAdmin = Usuario.builder().nome("Usuario Administrador").password("12345").username("admin").build();
		Usuario usuarioComun = Usuario.builder().nome("Usuario Comun").password("54321").username("comun").build();
		usuarioAdmin.addRole(Role.ADMIN);
		usuarioComun.addRole(Role.COMMON);
		
		usuarioService.save(usuarioAdmin);
		usuarioService.save(usuarioComun);
		
	}

}
