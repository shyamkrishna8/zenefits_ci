package com.shyam.zenefits.ci.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class TestController {

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	@ResponseBody
	public String projectStatus() {
		return "Project is up and running";
	}

}
