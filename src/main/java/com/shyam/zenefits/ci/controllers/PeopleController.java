package com.shyam.zenefits.ci.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.zenefits.ci.managers.PeopleManager;
import com.shyam.zenefits.ci.requests.GetPeoplesReq;
import com.shyam.zenefits.ci.response.BasicListResponse;


@CrossOrigin(methods = { RequestMethod.GET }, origins = { "http://localhost"}, allowCredentials = "true")
@RestController
@RequestMapping("/people")
public class PeopleController {

	@Autowired
	private PeopleManager peopleManager;

	@RequestMapping(value = "/{companyId}/get", method = RequestMethod.GET)
	@ResponseBody
	public BasicListResponse getCompanyPeople(@PathVariable("companyId") String companyId,
			@ModelAttribute GetPeoplesReq getPeoplesReq) throws Exception {
		// TODO : Authentication
		return peopleManager.getCompanyPeople(companyId, getPeoplesReq);
	}
}
