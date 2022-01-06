package com.crud.client.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crud.client.services.ClienteService;

@Component
public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, String>{

	@Autowired
	private ClienteService clienteService;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {	
		boolean existsCpf = clienteService.hasCpf(value);
		return !existsCpf;
	}

}
