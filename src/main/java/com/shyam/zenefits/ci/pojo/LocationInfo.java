package com.shyam.zenefits.ci.pojo;

import org.json.JSONObject;

import com.google.gson.annotations.Expose;
import com.shyam.zenefits.ci.utils.ZenefitsUtils;

public class LocationInfo extends AbstractZenifitsEntity {
	private String city;
	private String country;
	private String name;
	private String phone;
	private String state;
	private String street1;
	private String street2;
	private String zip;

	@Expose(deserialize = false)
	private String company;

	@Expose(deserialize = false)
	private String people;

	public LocationInfo() {
		super();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	@Override
	public String toString() {
		return "LocationInfo [city=" + city + ", country=" + country + ", name=" + name + ", phone=" + phone
				+ ", state=" + state + ", street1=" + street1 + ", street2=" + street2 + ", zip=" + zip + ", company="
				+ company + ", people=" + people + ", toString()=" + super.toString() + "]";
	}

	@Override
	public void processNavigationUrls(JSONObject responseObject)
			throws IllegalArgumentException, IllegalAccessException {
		this.company = ZenefitsUtils.getUrlFromResponseObject(responseObject, "company");
		this.people = ZenefitsUtils.getUrlFromResponseObject(responseObject, "people");
	}
}
