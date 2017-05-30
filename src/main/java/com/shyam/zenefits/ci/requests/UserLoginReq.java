package com.shyam.zenefits.ci.requests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.shyam.zenefits.ci.exceptions.BadRequestException;

public class UserLoginReq {
	private String emailId;
	private String password;

	public UserLoginReq() {
		super();
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void validate() throws BadRequestException {
		List<String> errors = collectErrors();
		if (!CollectionUtils.isEmpty(errors)) {
			throw new BadRequestException("Invalid params:" + Arrays.toString(errors.toArray()));
		}
	}

	public List<String> collectErrors() {
		List<String> errors = new ArrayList<String>();
		if (StringUtils.isEmpty(emailId)) {
			errors.add("emailId");
		}
		if (StringUtils.isEmpty(password)) {
			errors.add("password");
		}

		return errors;
	}

	@Override
	public String toString() {
		return "UserLoginReq [emailId=" + emailId + ", password=" + password + ", toString()=" + super.toString() + "]";
	}
}
