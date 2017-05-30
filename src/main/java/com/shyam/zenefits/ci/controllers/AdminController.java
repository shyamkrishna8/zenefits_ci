package com.shyam.zenefits.ci.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.zenefits.ci.managers.UserManager;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserManager userManager;

	private static final String PASS_PHRASE = "ZENEFITS_CI_PASS_PHRASE_321";

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	@ResponseBody
	public String projectStatus() {
		return "Project is up and running";
	}

	@RequestMapping(value = "/init", method = RequestMethod.POST)
	@ResponseBody
	public void initUsers(@RequestParam(name = "passphrase", required = true) String passPhrase) {
		if (!PASS_PHRASE.equals(passPhrase)) {
			return;
		}

		userManager.initUser();
	}
}
