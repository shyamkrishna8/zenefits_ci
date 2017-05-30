package com.shyam.zenefits.ci.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.zenefits.ci.connector.EmployeeConnector;
import com.shyam.zenefits.ci.exceptions.ZException;
import com.shyam.zenefits.ci.pojo.EmployeeBankAccount;
import com.shyam.zenefits.ci.pojo.EmployeeDetails;

@Service
public class EmployeeManager extends AbstractManager {
	@Autowired
	private EmployeeConnector employeeConnector;

	public List<EmployeeDetails> getEmployeeDetails(String personId) throws ZException {
		return employeeConnector.getEmployeeDetails(personId);
	}

	public List<EmployeeBankAccount> getEmployeeBankAccounts(String personId) throws ZException {
		return employeeConnector.getEmployeeBankAccount(personId);
	}
}
