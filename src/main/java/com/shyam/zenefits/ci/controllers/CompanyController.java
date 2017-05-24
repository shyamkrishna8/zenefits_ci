package com.shyam.zenefits.ci.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.zenefits.ci.managers.CompanyManager;
import com.shyam.zenefits.ci.pojo.CompanyBasicInfo;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyManager companyManager;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public List<CompanyBasicInfo> getCoreCompanies() throws Exception {
		// TODO : Authentication
		return companyManager.getCoreCompanies();
	}
}
