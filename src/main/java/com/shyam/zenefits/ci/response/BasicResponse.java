package com.shyam.zenefits.ci.response;

import com.shyam.zenefits.ci.exceptions.ErrorCode;

public class BasicResponse {
	private boolean success = true;
	private Object response;
	private String errorCode;

	public BasicResponse() {
		super();
	}

	public BasicResponse(Object response, ErrorCode errorCode) {
		super();
		this.response = response;
		this.errorCode = (errorCode ==null) ? null : errorCode.name();
		this.success = (errorCode == null) ? true : false;
	}

	public BasicResponse(Object response) {
		this(response, null);
	}

	public BasicResponse(ErrorCode errorCode) {
		this(null, errorCode);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "BasicResponse [success=" + success + ", response=" + response + ", errorCode=" + errorCode
				+ ", toString()=" + super.toString() + "]";
	}
}
