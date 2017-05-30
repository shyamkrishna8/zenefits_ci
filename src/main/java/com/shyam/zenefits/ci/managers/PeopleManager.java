package com.shyam.zenefits.ci.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.zenefits.ci.connector.PeopleConnector;
import com.shyam.zenefits.ci.exceptions.ZException;
import com.shyam.zenefits.ci.requests.GetPeoplesReq;
import com.shyam.zenefits.ci.response.BasicListResponse;

@Service
public class PeopleManager {

	@Autowired
	private PeopleConnector peopleConnector;


	public BasicListResponse getCompanyPeople(String companyId, GetPeoplesReq getPeoplesReq) throws ZException {
		return peopleConnector.getCompanyPeople(companyId, getPeoplesReq);
	}
}
