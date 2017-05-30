package com.shyam.zenefits.ci.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.zenefits.ci.dao.UserDAO;
import com.shyam.zenefits.ci.entities.User;

@Service
public class UserManager {

	@Autowired
	private UserDAO userDAO;

	private static final String DEFAULT_PASSWORD = "zenefits123";

	public void initUser() {
		User user = User.createDefaultUser();
		user.updatePasswordHashFromPassword(DEFAULT_PASSWORD);
		userDAO.create(user);
		System.out.println("Init user created:" + user.toString());
	}

	
}
