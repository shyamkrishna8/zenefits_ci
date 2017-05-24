package com.shyam.zenefits.ci.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.zenefits.ci.connector.PeopleConnector;
import com.shyam.zenefits.ci.pojo.PersonInfo;

@Service
public class PeopleManager {

	@Autowired
	private PeopleConnector peopleConnector;


	public List<PersonInfo> getCompanyPeople(String companyId) throws Exception {
		return peopleConnector.getCompanyPeople(companyId);
	}
}
