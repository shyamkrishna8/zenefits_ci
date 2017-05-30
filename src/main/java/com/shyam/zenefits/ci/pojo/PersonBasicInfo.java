package com.shyam.zenefits.ci.pojo;

import org.json.JSONObject;

import com.google.gson.annotations.Expose;

public class PersonBasicInfo extends AbstractZenifitsEntity {
	@Expose
	private String preferred_name;
	private String postal_code;
	private String city;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String title;
	private String work_phone;
	private String personal_email;
	private String street1;
	private String type;
	private String status;
	private String street2;
	private String photo_thumbnail_url;
	private String personal_phone;
	private String federal_filing_status;
	private String work_email;
	private String photo_url;
	private String gender;
	private String country;

	public PersonBasicInfo() {
		super();
	}

	public String getPreferred_name() {
		return preferred_name;
	}

	public void setPreferred_name(String preferred_name) {
		this.preferred_name = preferred_name;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWork_phone() {
		return work_phone;
	}

	public void setWork_phone(String work_phone) {
		this.work_phone = work_phone;
	}

	public String getPersonal_email() {
		return personal_email;
	}

	public void setPersonal_email(String personal_email) {
		this.personal_email = personal_email;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getPhoto_thumbnail_url() {
		return photo_thumbnail_url;
	}

	public void setPhoto_thumbnail_url(String photo_thumbnail_url) {
		this.photo_thumbnail_url = photo_thumbnail_url;
	}

	public String getPersonal_phone() {
		return personal_phone;
	}

	public void setPersonal_phone(String personal_phone) {
		this.personal_phone = personal_phone;
	}

	public String getFederal_filing_status() {
		return federal_filing_status;
	}

	public void setFederal_filing_status(String federal_filing_status) {
		this.federal_filing_status = federal_filing_status;
	}

	public String getWork_email() {
		return work_email;
	}

	public void setWork_email(String work_email) {
		this.work_email = work_email;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	@Override
	public String toString() {
		return "PersonBasicInfo [preferred_name=" + preferred_name + ", postal_code=" + postal_code + ", city=" + city
				+ ", first_name=" + first_name + ", middle_name=" + middle_name + ", last_name=" + last_name
				+ ", title=" + title + ", work_phone=" + work_phone + ", personal_email=" + personal_email
				+ ", street1=" + street1 + ", type=" + type + ", status=" + status + ", street2=" + street2
				+ ", photo_thumbnail_url=" + photo_thumbnail_url + ", personal_phone=" + personal_phone
				+ ", federal_filing_status=" + federal_filing_status + ", work_email=" + work_email + ", photo_url="
				+ photo_url + ", gender=" + gender + ", country=" + country + ", toString()=" + super.toString() + "]";
	}

	@Override
	public void processNavigationUrls(JSONObject responseObject)
			throws IllegalArgumentException, IllegalAccessException {

	}

	// private String manager; // TODO
	// private String date_of_birth; // TODO
	// private String department; // TODO
	// private String employments; // TODO
	// private String subordinates; // TODO
	// private String company; // TODO

}
