package com.shyam.zenefits.ci.pojo;

import org.json.JSONObject;

public class CompanyBasicInfo extends AbstractZenifitsEntity {
	private String ein;
	private String legal_city;
	private String legal_name;
	private String legal_state;
	private String legal_street1;
	private String legal_street2;
	private String legal_zip;
	private String logo_url;
	private String name;

	public CompanyBasicInfo() {
		super();
	}

	public String getEin() {
		return ein;
	}

	public void setEin(String ein) {
		this.ein = ein;
	}

	public String getLegal_city() {
		return legal_city;
	}

	public void setLegal_city(String legal_city) {
		this.legal_city = legal_city;
	}

	public String getLegal_name() {
		return legal_name;
	}

	public void setLegal_name(String legal_name) {
		this.legal_name = legal_name;
	}

	public String getLegal_state() {
		return legal_state;
	}

	public void setLegal_state(String legal_state) {
		this.legal_state = legal_state;
	}

	public String getLegal_street1() {
		return legal_street1;
	}

	public void setLegal_street1(String legal_street1) {
		this.legal_street1 = legal_street1;
	}

	public String getLegal_street2() {
		return legal_street2;
	}

	public void setLegal_street2(String legal_street2) {
		this.legal_street2 = legal_street2;
	}

	public String getLegal_zip() {
		return legal_zip;
	}

	public void setLegal_zip(String legal_zip) {
		this.legal_zip = legal_zip;
	}

	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CompanyInfo [ein=" + ein + ", legal_city=" + legal_city + ", legal_name=" + legal_name
				+ ", legal_state=" + legal_state + ", legal_street1=" + legal_street1 + ", legal_street2="
				+ legal_street2 + ", legal_zip=" + legal_zip + ", logo_url=" + logo_url + ", name=" + name
				+ ", toString()=" + super.toString() + "]";
	}

	@Override
	public void processNavigationUrls(JSONObject responseObject)
			throws IllegalArgumentException, IllegalAccessException {
		
	}
}
