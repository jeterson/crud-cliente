package com.crud.client.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.client.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Optional<Cliente> findByCpf(String cpf);
}
