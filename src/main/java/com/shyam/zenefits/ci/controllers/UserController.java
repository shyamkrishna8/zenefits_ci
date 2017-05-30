package com.shyam.zenefits.ci.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.zenefits.ci.entities.User;
import com.shyam.zenefits.ci.exceptions.BadRequestException;
import com.shyam.zenefits.ci.exceptions.ConflictException;
import com.shyam.zenefits.ci.managers.UserManager;
import com.shyam.zenefits.ci.requests.UserLoginReq;
import com.shyam.zenefits.ci.response.BasicResponse;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserManager userManager;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public BasicResponse validateLogin(@RequestBody UserLoginReq req, HttpServletRequest request)
			throws BadRequestException, ConflictException {

		// Validate request
		req.validate();

		User user = userManager.validateLogin(req.getEmailId(), req.getPassword(), request);

		// Create session
		return new BasicResponse(user);
	}
}
