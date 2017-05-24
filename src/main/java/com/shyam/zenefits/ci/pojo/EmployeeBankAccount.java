package com.shyam.zenefits.ci.pojo;

import org.json.JSONObject;

import com.google.gson.annotations.Expose;
import com.shyam.zenefits.ci.utils.ZenefitsUtils;

public class EmployeeBankAccount extends BankAccount {

	@Expose(deserialize = false)
	private String person;

	public EmployeeBankAccount() {
		super();
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "EmployeeBankAccount [person=" + person + ", toString()=" + super.toString() + "]";
	}

	@Override
	public void processNavigationUrls(JSONObject responseObject)
			throws IllegalArgumentException, IllegalAccessException {
		this.person = ZenefitsUtils.getUrlFromResponseObject(responseObject, "person");
	}
}
