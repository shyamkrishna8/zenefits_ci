package com.shyam.zenefits.ci.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.zenefits.ci.connector.CompanyConnector;
import com.shyam.zenefits.ci.pojo.CompanyInfo;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyConnector companiesConnector;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public List<CompanyInfo> getCoreCompanies() {
		// TODO : Authentication
		return companiesConnector.getCoreCompanies();
	}
}
