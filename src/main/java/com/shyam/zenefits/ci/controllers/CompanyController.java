package com.shyam.zenefits.ci.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.zenefits.ci.exceptions.ZException;
import com.shyam.zenefits.ci.managers.CompanyManager;
import com.shyam.zenefits.ci.pojo.CompanyBankAccount;
import com.shyam.zenefits.ci.pojo.CompanyBasicInfo;
import com.shyam.zenefits.ci.pojo.DepartmentInfo;
import com.shyam.zenefits.ci.pojo.LocationInfo;
import com.shyam.zenefits.ci.response.BasicResponse;

@CrossOrigin(methods = { RequestMethod.GET }, origins = { "http://localhost" }, allowCredentials = "true")
@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyManager companyManager;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public BasicResponse getCoreCompanies() throws ZException {
		// TODO : Authentication
		List<CompanyBasicInfo> companyInfo = companyManager.getCoreCompanies();
		return new BasicResponse(companyInfo);
	}

	@RequestMapping(value = "/{companyId}/banks", method = RequestMethod.GET)
	@ResponseBody
	public BasicResponse getComapnyBankAccount(@PathVariable("companyId") String companyId) throws Exception {
		// TODO : Authentication
		List<CompanyBankAccount> bankAccounts = companyManager.getCompanyBankAccount(companyId);
		return new BasicResponse(bankAccounts);
	}

	@RequestMapping(value = "/{companyId}/departments", method = RequestMethod.GET)
	@ResponseBody
	public BasicResponse getComapnyDepartments(@PathVariable("companyId") String companyId) throws Exception {
		// TODO : Authentication
		List<DepartmentInfo> departments = companyManager.getCompanyDepartments(companyId);
		return new BasicResponse(departments);
	}

	@RequestMapping(value = "/{companyId}/locations", method = RequestMethod.GET)
	@ResponseBody
	public BasicResponse getComapnyLocations(@PathVariable("companyId") String companyId) throws Exception {
		// TODO : Authentication
		List<LocationInfo> locationInfos = companyManager.getCompanyLocations(companyId);
		return new BasicResponse(locationInfos);
	}
}
