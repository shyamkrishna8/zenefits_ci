package com.shyam.zenefits.ci.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends ZException {

	private static final long serialVersionUID = -3080397519861070679L;

	public BadRequestException(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public BadRequestException(String errorMessage) {
		this(ErrorCode.BAD_REQUEST_ERROR, errorMessage);
	}
}
