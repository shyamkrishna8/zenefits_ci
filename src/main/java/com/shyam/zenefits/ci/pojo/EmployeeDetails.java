package com.shyam.zenefits.ci.pojo;

import org.json.JSONObject;

import com.google.gson.annotations.Expose;
import com.shyam.zenefits.ci.utils.ZenefitsUtils;

public class EmployeeDetails extends AbstractZenifitsEntity {
	private Float annual_salary;
	private String comp_type;
	private String employment_type;
	private String pay_rate; // TODO : Find the type
	private Integer working_hours_per_week;
	private String termination_type;
	private String hire_date;
	private String termination_date;

	@Expose(serialize = true, deserialize = false)
	private String person;

	public EmployeeDetails() {
		super();
	}

	public Float getAnnual_salary() {
		return annual_salary;
	}

	public void setAnnual_salary(Float annual_salary) {
		this.annual_salary = annual_salary;
	}

	public String getComp_type() {
		return comp_type;
	}

	public void setComp_type(String comp_type) {
		this.comp_type = comp_type;
	}

	public String getEmployment_type() {
		return employment_type;
	}

	public void setEmployment_type(String employment_type) {
		this.employment_type = employment_type;
	}

	public String getPay_rate() {
		return pay_rate;
	}

	public void setPay_rate(String pay_rate) {
		this.pay_rate = pay_rate;
	}

	public Integer getWorking_hours_per_week() {
		return working_hours_per_week;
	}

	public void setWorking_hours_per_week(Integer working_hours_per_week) {
		this.working_hours_per_week = working_hours_per_week;
	}

	public String getTermination_type() {
		return termination_type;
	}

	public void setTermination_type(String termination_type) {
		this.termination_type = termination_type;
	}

	public String getHire_date() {
		return hire_date;
	}

	public void setHire_date(String hire_date) {
		this.hire_date = hire_date;
	}

	public String getTermination_date() {
		return termination_date;
	}

	public void setTermination_date(String termination_date) {
		this.termination_date = termination_date;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Override
	public void processNavigationUrls(JSONObject responseObject)
			throws IllegalArgumentException, IllegalAccessException {
		this.person = ZenefitsUtils.getUrlFromResponseObject(responseObject, "person");
	}

	@Override
	public String toString() {
		return "EmployeeDetails [annual_salary=" + annual_salary + ", comp_type=" + comp_type + ", employment_type="
				+ employment_type + ", pay_rate=" + pay_rate + ", working_hours_per_week=" + working_hours_per_week
				+ ", termination_type=" + termination_type + ", hire_date=" + hire_date + ", termination_date="
				+ termination_date + ", person=" + person + ", toString()=" + super.toString() + "]";
	}
}
