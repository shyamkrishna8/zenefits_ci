package com.shyam.zenefits.ci.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends ZException {

	private static final long serialVersionUID = -5801090389297925983L;

	public InternalServerException(String errorMessage) {
		this(ErrorCode.SERVICE_ERROR, errorMessage);
	}

	public InternalServerException(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}
}
