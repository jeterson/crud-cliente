package com.crud.client.resources.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationStandardError extends StandardError {

	private List<FieldError> errors = new ArrayList<>();
}
