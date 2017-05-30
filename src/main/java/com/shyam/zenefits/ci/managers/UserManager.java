package com.shyam.zenefits.ci.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.shyam.zenefits.ci.dao.UserDAO;
import com.shyam.zenefits.ci.entities.User;
import com.shyam.zenefits.ci.exceptions.ForbiddenException;

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

	public User validateLogin(String emailId, String password) throws ForbiddenException {
		Query query = new Query();
		query.addCriteria(Criteria.where(User.FieldConstants.EMAIL_ID).is(emailId));
		query.addCriteria(Criteria.where(User.FieldConstants.PASSWORD_HASH).is(User.encrypt(password)));

		List<User> users = userDAO.runQuery(query, User.class);
		if (CollectionUtils.isEmpty(users)) {
			throw new ForbiddenException("Invalid username or password");
		}

		return users.get(0);
	}
}
