package com.shyam.zenefits.ci.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends ZException {

	private static final long serialVersionUID = -6587938517250592765L;

	public NotFoundException(String errorMessage) {
		super(ErrorCode.NOT_FOUND_ERROR, errorMessage);
	}
}
