package com.shyam.zenefits.ci.connector;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shyam.zenefits.ci.exceptions.InternalServerException;
import com.shyam.zenefits.ci.exceptions.ZException;
import com.shyam.zenefits.ci.pojo.EmployeeBankAccount;
import com.shyam.zenefits.ci.pojo.EmployeeDetails;

@Service
public class EmployeeConnector extends AbstractZenefitsConnector {

	private static final String GET_PEOPLE_API_URL = API_BASE_URL + "/core/people"; // https://api.zenefits.com/core/people/1925385/employments

	@PostConstruct
	public void init() {
	}

	public List<EmployeeDetails> getEmployeeDetails(String personId) throws ZException {
		String output = getWebData(getEmployeeUrl(personId));
		return parseEmployeeDetailsResponse(output);
	}

	public List<EmployeeBankAccount> getEmployeeBankAccount(String personId) throws ZException {
		// Parse the response
		String output = getWebData(getEmployeeBankAccountUrl(personId));
		return parseEmployeeBankAccountResponse(output);
	}

	private static List<EmployeeDetails> parseEmployeeDetailsResponse(String output) throws InternalServerException {
		List<EmployeeDetails> results = new ArrayList<>();
		if (StringUtils.isEmpty(output)) {
			return results;
		}

		// Re-validate the status
		JSONObject jsonObject = new JSONObject(output);
		int status = jsonObject.getInt("status");
		if (status != HttpStatus.OK.value()) {
			throw new InternalServerException("Error occured fetching the details. Kindly try after some time");
		}

		JSONObject dataObject = jsonObject.getJSONObject("data");
		JSONArray employeeData = dataObject.getJSONArray("data");

		System.out.println("Output:" + employeeData.toString());
		try {
			if (employeeData.length() > 0) {
				for (int i = 0; i < employeeData.length(); i++) {
					JSONObject jsonEmployeeEntry = employeeData.getJSONObject(i);
					EmployeeDetails employeeInfo = gson.fromJson(jsonEmployeeEntry.toString(), EmployeeDetails.class);
					employeeInfo.processNavigationUrls(jsonEmployeeEntry);
					results.add(employeeInfo);
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException ex) {
			throw new InternalServerException("Error parsing the navigation urls");
		}
		return results;
	}

	private static List<EmployeeBankAccount> parseEmployeeBankAccountResponse(String output)
			throws InternalServerException {
		List<EmployeeBankAccount> results = new ArrayList<>();
		if (StringUtils.isEmpty(output)) {
			return results;
		}

		// Re-validate the status
		JSONObject jsonObject = new JSONObject(output);
		int status = jsonObject.getInt("status");
		if (status != HttpStatus.OK.value()) {
			throw new InternalServerException("Error occured fetching the details. Kindly try after some time");
		}

		JSONObject dataObject = jsonObject.getJSONObject("data");
		JSONArray employeeBankAccountData = dataObject.getJSONArray("data");

		System.out.println("Output:" + employeeBankAccountData.toString());
		try {
			if (employeeBankAccountData.length() > 0) {
				for (int i = 0; i < employeeBankAccountData.length(); i++) {
					JSONObject jsonEmployeeBankAccountEntry = employeeBankAccountData.getJSONObject(i);
					EmployeeBankAccount employeeBankAccount = gson.fromJson(jsonEmployeeBankAccountEntry.toString(),
							EmployeeBankAccount.class);
					employeeBankAccount.processNavigationUrls(jsonEmployeeBankAccountEntry);
					results.add(employeeBankAccount);
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException ex) {
			throw new InternalServerException("Error parsing the navigation urls");
		}
		return results;
	}

	private static String getEmployeeUrl(String personId) {
		return GET_PEOPLE_API_URL + "/" + personId + "/employments";
	}

	private static String getEmployeeBankAccountUrl(String personId) {
		return GET_PEOPLE_API_URL + "/" + personId + "/banks";
	}
}
