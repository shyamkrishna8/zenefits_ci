package com.shyam.zenefits.ci.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.shyam.zenefits.ci.utils.Constants;

@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = {
		Constants.LOCAL_ADDRESS, Constants.REMOTE_ADDRESS }, allowCredentials = "true")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserManager userManager;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BasicResponse validateLogin(@RequestBody UserLoginReq req, HttpServletRequest request)
			throws BadRequestException, ConflictException {

		// Validate request
		req.validate();

		User user = userManager.validateLogin(req.getEmailId(), req.getPassword(), request);

		// Create session
		return new BasicResponse(user);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BasicResponse logout(HttpServletRequest request) throws BadRequestException {

		userManager.destroySession(request);

		// Create session
		return new BasicResponse();
	}

}
