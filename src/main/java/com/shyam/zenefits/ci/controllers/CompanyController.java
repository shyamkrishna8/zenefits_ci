package com.shyam.zenefits.ci.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.zenefits.ci.exceptions.ZException;
import com.shyam.zenefits.ci.managers.CompanyManager;
import com.shyam.zenefits.ci.managers.UserManager;
import com.shyam.zenefits.ci.pojo.CompanyBankAccount;
import com.shyam.zenefits.ci.pojo.CompanyBasicInfo;
import com.shyam.zenefits.ci.pojo.DepartmentInfo;
import com.shyam.zenefits.ci.pojo.LocationInfo;
import com.shyam.zenefits.ci.response.BasicResponse;
import com.shyam.zenefits.ci.utils.Constants;

@CrossOrigin(methods = { RequestMethod.GET }, origins = { Constants.LOCAL_ADDRESS, Constants.REMOTE_ADDRESS }, allowCredentials = "true")
@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private CompanyManager companyManager;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public BasicResponse getCoreCompanies(HttpServletRequest request) throws ZException {
		userManager.validateSession(request);
		List<CompanyBasicInfo> companyInfo = companyManager.getCoreCompanies();
		return new BasicResponse(companyInfo);
	}

	@RequestMapping(value = "/{companyId}/banks", method = RequestMethod.GET)
	@ResponseBody
	public BasicResponse getComapnyBankAccount(@PathVariable("companyId") String companyId, HttpServletRequest request)
			throws Exception {
		userManager.validateSession(request);
		List<CompanyBankAccount> bankAccounts = companyManager.getCompanyBankAccount(companyId);
		return new BasicResponse(bankAccounts);
	}

	@RequestMapping(value = "/{companyId}/departments", method = RequestMethod.GET)
	@ResponseBody
	public BasicResponse getComapnyDepartments(@PathVariable("companyId") String companyId, HttpServletRequest request)
			throws Exception {
		userManager.validateSession(request);
		List<DepartmentInfo> departments = companyManager.getCompanyDepartments(companyId);
		return new BasicResponse(departments);
	}

	@RequestMapping(value = "/{companyId}/locations", method = RequestMethod.GET)
	@ResponseBody
	public BasicResponse getComapnyLocations(@PathVariable("companyId") String companyId, HttpServletRequest request)
			throws Exception {
		userManager.validateSession(request);
		List<LocationInfo> locationInfos = companyManager.getCompanyLocations(companyId);
		return new BasicResponse(locationInfos);
	}
}
