package com.shyam.zenefits.ci.exceptions;

public abstract class ZException extends Exception {

	private ErrorCode errorCode;
	private String errorMessage;

	public ZException(ErrorCode errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public ZException(ErrorCode errorCode) {
		this(errorCode, null);
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ZException [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", toString()="
				+ super.toString() + "]";
	}
}
