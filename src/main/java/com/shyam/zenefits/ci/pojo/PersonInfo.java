package com.shyam.zenefits.ci.pojo;

import java.lang.reflect.Field;

import org.json.JSONObject;

import com.google.gson.annotations.Expose;
import com.shyam.zenefits.ci.utils.ZenefitsUtils;

public class PersonInfo extends PersonBasicInfo {

    @Expose(serialize = true, deserialize=false)
	private String location;

    @Expose(serialize = true, deserialize=false)
	private String department;

    @Expose(serialize = true, deserialize=false)
	private String employments;

    @Expose(serialize = true, deserialize=false)
	private String company;

    @Expose(serialize = true, deserialize=false)
	private String subordinates;

	public PersonInfo() {
		super();
	}

	public String getLocationUrl() {
		return location;
	}

	public void setLocationUrl(String locationUrl) {
		this.location = locationUrl;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmployments() {
		return employments;
	}

	public void setEmployments(String employments) {
		this.employments = employments;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(String subordinates) {
		this.subordinates = subordinates;
	}

	@Override
	public void processNavigationUrls(JSONObject responseObject) throws IllegalArgumentException, IllegalAccessException {
		for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            field.set(this, ZenefitsUtils.getUrlFromResponseObject(responseObject, field.getName()));
		}
	}

	@Override
	public String toString() {
		return "PersonInfo [location=" + location + ", department=" + department + ", employments=" + employments
				+ ", company=" + company + ", subordinates=" + subordinates + ", toString()=" + super.toString() + "]";
	}

	/*
	 * "location": { "url": null, "object": "/meta/ref/detail", "ref_object":
	 * "/core/locations" }, "department": { "url": null, "object":
	 * "/meta/ref/detail", "ref_object": "/core/departments" }, "employments": {
	 * "url": "https://api.zenefits.com/core/people/1925385/employments", "object":
	 * "/meta/ref/list", "ref_object": "/core/employments" }, "company": { "url":
	 * "https://api.zenefits.com/core/companies/131492", "object":
	 * "/meta/ref/detail", "ref_object": "/core/companies" }, "subordinates": {
	 * "url": "https://api.zenefits.com/core/people/1925385/subordinates", "object":
	 * "/meta/ref/list", "ref_object": "/core/people" },
	 */
}
