package com.shyam.zenefits.ci.pojo;

import org.json.JSONObject;

import com.google.gson.annotations.Expose;
import com.shyam.zenefits.ci.utils.ZenefitsUtils;

public abstract class CompanyBankAccount extends BankAccount {

	@Expose(deserialize = false)
	private String company;

	public CompanyBankAccount() {
		super();
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public void processNavigationUrls(JSONObject responseObject)
			throws IllegalArgumentException, IllegalAccessException {
		this.company = ZenefitsUtils.getUrlFromResponseObject(responseObject, "company");
	}

}
