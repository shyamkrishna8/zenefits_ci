package com.shyam.zenefits.ci.pojo;

import org.json.JSONObject;

import com.google.gson.annotations.Expose;
import com.shyam.zenefits.ci.utils.ZenefitsUtils;

public class DepartmentInfo extends AbstractZenifitsEntity {
	private String name;

	@Expose(serialize=true, deserialize = false)
	private String people;

	@Expose(serialize=true, deserialize = false)
	private String company;

	public DepartmentInfo() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "DepartmentInfo [name=" + name + ", people=" + people + ", company=" + company + ", toString()="
				+ super.toString() + "]";
	}

	@Override
	public void processNavigationUrls(JSONObject responseObject)
			throws IllegalArgumentException, IllegalAccessException {
		this.people = ZenefitsUtils.getUrlFromResponseObject(responseObject, "people");
		this.company = ZenefitsUtils.getUrlFromResponseObject(responseObject, "company");
	}
}
