package com.shyam.zenefits.ci.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.zenefits.ci.managers.EmployeeManager;
import com.shyam.zenefits.ci.pojo.EmployeeBankAccount;
import com.shyam.zenefits.ci.pojo.EmployeeDetails;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeManager employeeManager;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public List<EmployeeDetails> getEmployeeDetails(
			@RequestParam(name = "employeeId", required = true) String employeeId)
			throws Exception {
		// TODO : Authentication
		return employeeManager.getEmployeeDetails(employeeId);
	}

	@RequestMapping(value = "/banks", method = RequestMethod.GET)
	@ResponseBody
	public List<EmployeeBankAccount> getComapnyBankAccount(
			@RequestParam(name = "personId", required = true) String personId) throws Exception {
		// TODO : Authentication
		return employeeManager.getEmployeeBankAccounts(personId);
	}
}
