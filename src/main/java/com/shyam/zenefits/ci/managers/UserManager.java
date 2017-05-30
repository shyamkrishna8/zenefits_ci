package com.shyam.zenefits.ci.managers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.shyam.zenefits.ci.dao.UserDAO;
import com.shyam.zenefits.ci.entities.User;
import com.shyam.zenefits.ci.exceptions.BadRequestException;
import com.shyam.zenefits.ci.exceptions.ConflictException;
import com.shyam.zenefits.ci.exceptions.ErrorCode;
import com.shyam.zenefits.ci.exceptions.ForbiddenException;
import com.shyam.zenefits.ci.utils.Constants;

@Service
public class UserManager {

	@Autowired
	private UserDAO userDAO;

	private static final String DEFAULT_PASSWORD = "zenefits123";
	private static final String USER_EMAIL_SESSION_PARAM = "userEmail";

	private static final Gson gson = new Gson();

	public void initUser() {
		User user = User.createDefaultUser();
		user.updatePasswordHashFromPassword(DEFAULT_PASSWORD);
		userDAO.create(user);
		System.out.println("Init user created:" + user.toString());
	}

	public User validateLogin(String emailId, String password, HttpServletRequest request)
			throws BadRequestException, ConflictException {
		Query query = new Query();
		query.addCriteria(Criteria.where(User.FieldConstants.EMAIL_ID).is(emailId));
		query.addCriteria(Criteria.where(User.FieldConstants.PASSWORD_HASH).is(User.encrypt(password)));

		List<User> users = userDAO.runQuery(query, User.class);
		if (CollectionUtils.isEmpty(users)) {
			throw new BadRequestException(ErrorCode.BAD_CREDENTIALS, "Invalid username or password");
		}

		User user = users.get(0);
		createSession(user, request);
		return user;
	}

	public void createSession(User user, HttpServletRequest request) throws ConflictException {
		HttpSession session = request.getSession();
		Object previousContext = session.getAttribute(Constants.SPRING_SECURITY_CONTEXT);
		Object emailId = session.getAttribute(USER_EMAIL_SESSION_PARAM);
		if (previousContext != null && !StringUtils.isEmpty(previousContext) && emailId != null
				&& !StringUtils.isEmpty(emailId)) {
			throw new ConflictException(ErrorCode.USER_ALREADY_LOGGED_IN, "User already logged in");
		} else {
			session.setAttribute(Constants.SPRING_SECURITY_CONTEXT, gson.toJson(user));
			session.setAttribute(USER_EMAIL_SESSION_PARAM, user.getEmailId());
		}
	}

	public void validateSession(HttpServletRequest request) throws ForbiddenException {
		HttpSession session = request.getSession();
		SecurityContext previousContext = (SecurityContext) session.getAttribute(Constants.SPRING_SECURITY_CONTEXT);
		Object emailId = session.getAttribute(USER_EMAIL_SESSION_PARAM);
		if (previousContext == null || emailId == null || StringUtils.isEmpty(emailId)) {
			throw new ForbiddenException(ErrorCode.NOT_LOGGED_IN, "No user logged in");
		}
	}
}
