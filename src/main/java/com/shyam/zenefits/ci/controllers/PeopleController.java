package com.shyam.zenefits.ci.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.zenefits.ci.managers.PeopleManager;
import com.shyam.zenefits.ci.managers.UserManager;
import com.shyam.zenefits.ci.requests.GetPeoplesReq;
import com.shyam.zenefits.ci.response.BasicListResponse;
import com.shyam.zenefits.ci.utils.Constants;

@CrossOrigin(methods = { RequestMethod.GET }, origins = { Constants.LOCAL_ADDRESS, Constants.REMOTE_ADDRESS }, allowCredentials = "true")
@RestController
@RequestMapping("/people")
public class PeopleController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private PeopleManager peopleManager;

	@RequestMapping(value = "/{companyId}/get", method = RequestMethod.GET)
	@ResponseBody
	public BasicListResponse getCompanyPeople(@PathVariable("companyId") String companyId,
			@ModelAttribute GetPeoplesReq getPeoplesReq, HttpServletRequest request) throws Exception {
		userManager.validateSession(request);
		return peopleManager.getCompanyPeople(companyId, getPeoplesReq);
	}
}
