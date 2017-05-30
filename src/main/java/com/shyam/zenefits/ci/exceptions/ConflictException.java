package com.shyam.zenefits.ci.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends ZException {

	private static final long serialVersionUID = -5631320963760962221L;

	public ConflictException(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public ConflictException(String errorMessage) {
		this(ErrorCode.CONFLICT_EXCEPTION, errorMessage);
	}
}
