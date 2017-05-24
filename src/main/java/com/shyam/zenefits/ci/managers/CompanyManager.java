package com.shyam.zenefits.ci.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.zenefits.ci.connector.CompanyConnector;
import com.shyam.zenefits.ci.pojo.CompanyBasicInfo;

@Service
public class CompanyManager {

	@Autowired
	private CompanyConnector companiesConnector;

	public List<CompanyBasicInfo> getCoreCompanies() throws Exception {
		return companiesConnector.getCoreCompanies();
	}

}
