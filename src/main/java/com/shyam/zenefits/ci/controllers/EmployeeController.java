package com.shyam.zenefits.ci.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.zenefits.ci.exceptions.ZException;
import com.shyam.zenefits.ci.managers.EmployeeManager;
import com.shyam.zenefits.ci.pojo.EmployeeBankAccount;
import com.shyam.zenefits.ci.pojo.EmployeeDetails;

@CrossOrigin(methods = { RequestMethod.GET }, origins = { "http://localhost"}, allowCredentials = "true")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeManager employeeManager;

	@RequestMapping(value = "/{personId}/get", method = RequestMethod.GET)
	@ResponseBody
	public List<EmployeeDetails> getEmployeeDetails(@PathVariable("personId") String personId)
			throws ZException {
		// TODO : Authentication
		return employeeManager.getEmployeeDetails(personId);
	}

	@RequestMapping(value = "/{personId}/banks", method = RequestMethod.GET)
	@ResponseBody
	public List<EmployeeBankAccount> getComapnyBankAccount(@PathVariable("personId") String personId) throws ZException {
		// TODO : Authentication
		return employeeManager.getEmployeeBankAccounts(personId);
	}
}
