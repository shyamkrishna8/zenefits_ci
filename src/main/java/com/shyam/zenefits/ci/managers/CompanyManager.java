package com.shyam.zenefits.ci.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.zenefits.ci.connector.CompanyConnector;
import com.shyam.zenefits.ci.exceptions.ZException;
import com.shyam.zenefits.ci.pojo.CompanyBankAccount;
import com.shyam.zenefits.ci.pojo.CompanyBasicInfo;
import com.shyam.zenefits.ci.pojo.DepartmentInfo;
import com.shyam.zenefits.ci.pojo.LocationInfo;

@Service
public class CompanyManager {

	@Autowired
	private CompanyConnector companiesConnector;

	public List<CompanyBasicInfo> getCoreCompanies() throws ZException {
		return companiesConnector.getCoreCompanies();
	}

	public List<CompanyBankAccount> getCompanyBankAccount(String companyId) throws ZException {
		return companiesConnector.getCompanyBankAccount(companyId);
	}

	public List<DepartmentInfo> getCompanyDepartments(String companyId) throws ZException {
		return companiesConnector.getCompanyDepartments(companyId);
	}

	public List<LocationInfo> getCompanyLocations(String companyId) throws ZException {
		return companiesConnector.getCompanyLocations(companyId);
	}
}
