package com.crud.client.resources.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldError {

	public String field;
	public String message;
}
