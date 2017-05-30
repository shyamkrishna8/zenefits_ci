package com.shyam.zenefits.ci.exceptions;

public class BadRequestException extends ZException {

	private static final long serialVersionUID = -3080397519861070679L;

	public BadRequestException(ErrorCode errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public BadRequestException(String errorMessage) {
		this(ErrorCode.BAD_REQUEST_ERROR, errorMessage);
	}
}
