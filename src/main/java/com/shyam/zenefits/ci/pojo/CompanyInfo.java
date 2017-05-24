package com.shyam.zenefits.ci.pojo;

import java.lang.reflect.Field;

import org.json.JSONObject;

import com.google.gson.annotations.Expose;
import com.shyam.zenefits.ci.utils.ZenefitsUtils;

public class CompanyInfo extends CompanyBasicInfo {
	@Expose(serialize = true, deserialize = false)
	private String locations;

	@Expose(serialize = true, deserialize = false)
	private String departments;

	@Expose(serialize = true, deserialize = false)
	private String people;

	public CompanyInfo() {
		super();
	}

	public String getLocations() {
		return locations;
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}

	public String getDepartments() {
		return departments;
	}

	public void setDepartments(String departments) {
		this.departments = departments;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	@Override
	public void processNavigationUrls(JSONObject responseObject)
			throws IllegalArgumentException, IllegalAccessException {
		for (Field field : this.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			field.set(this, ZenefitsUtils.getUrlFromResponseObject(responseObject, field.getName()));
		}
	}

	@Override
	public String toString() {
		return "CompanyInfo [locations=" + locations + ", departments=" + departments + ", people=" + people
				+ ", toString()=" + super.toString() + "]";
	}
}
