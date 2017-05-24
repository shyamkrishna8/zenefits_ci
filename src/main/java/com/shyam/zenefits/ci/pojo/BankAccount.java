package com.shyam.zenefits.ci.pojo;

public abstract class BankAccount extends AbstractZenifitsEntity {
	private String account_number;
	private String account_type;
	private String bank_name;
	private String routing_number;

	public BankAccount() {
		super();
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getRouting_number() {
		return routing_number;
	}

	public void setRouting_number(String routing_number) {
		this.routing_number = routing_number;
	}

	@Override
	public String toString() {
		return "BankAccount [account_number=" + account_number + ", account_type=" + account_type + ", bank_name="
				+ bank_name + ", routing_number=" + routing_number + ", toString()=" + super.toString() + "]";
	}
	
	/*
    "account_number": "1234567890",
    "account_type": "checking",
    "bank_name": "Fake Bank",
    "company": {
      "object": "/meta/ref/list",
      "ref_object": "/core/companies",
      "url": "https://api.zenefits.com/core/companies/1/"
    },
    "id": "54321",
    "routing_number": "021000029"*/

}
