package com.shyam.zenefits.ci.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends ZException {

	private static final long serialVersionUID = -8816157739314099253L;

	public ForbiddenException(String errorMessage) {
		super(ErrorCode.FORBIDDEN_ERROR, errorMessage);
	}
}
