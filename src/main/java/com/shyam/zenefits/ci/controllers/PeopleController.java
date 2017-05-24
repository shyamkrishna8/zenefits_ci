package com.shyam.zenefits.ci.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.zenefits.ci.managers.PeopleManager;
import com.shyam.zenefits.ci.pojo.PersonInfo;


@RestController
@RequestMapping("/people")
public class PeopleController {

	@Autowired
	private PeopleManager peopleManager;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public List<PersonInfo> getCompanyPeople(@RequestParam(name="companyId", required=true) String companyId) throws IllegalArgumentException, IllegalAccessException {
		// TODO : Authentication
		return peopleManager.getCompanyPeople(companyId);
	}
}
