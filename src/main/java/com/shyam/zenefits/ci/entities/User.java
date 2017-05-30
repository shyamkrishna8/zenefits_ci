package com.shyam.zenefits.ci.entities;

import java.security.MessageDigest;

import com.google.gson.annotations.Expose;
import com.shyam.zenefits.ci.utils.Constants;

public class User extends AbstractEntity {
	private String firstName;
	private String lastName;
	private String emailId;

	@Expose(serialize = false)
	private String passwordHash;

	public User() {
		super();
	}

	public User(String firstName, String lastName, String emailId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void updatePasswordHashFromPassword(String password) {
		this.passwordHash = encrypt(password);
	}

	public final static String encrypt(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance(Constants.PASSWORD_HASH_ALGORITHM);
			byte[] passBytes = password.getBytes();
			md.reset();
			byte[] digested = md.digest(passBytes);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < digested.length; i++) {
				sb.append(Integer.toHexString(0xff & digested[i]));
			}
			return sb.toString();
		} catch (Exception ex) {
			throw new InternalError("Internal server error");
		}
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public static User createDefaultUser() {
		return new User(Constants.DEFAULT_ADMIN_USER_FIRST_NAME, Constants.DEFAULT_ADMIN_USER_LAST_NAME,
				Constants.DEFAULT_ADMIN_USER);
	}

	public static class FieldConstants extends AbstractEntity.FieldConstants {
		public static final String EMAIL_ID = "emailId";
		public static final String PASSWORD_HASH = "passwordHash";
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", toString()="
				+ super.toString() + "]";
	}
}
