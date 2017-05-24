package com.shyam.zenefits.ci.connector;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shyam.zenefits.ci.pojo.EmployeeDetails;
import com.sun.jersey.api.client.ClientResponse;

@Service
public class EmployeeConnector extends AbstractZenefitsConnector {

	private static final String GET_PEOPLE_API_URL = API_BASE_URL + "/core/people"; // https://api.zenefits.com/core/people/1925385/employments

	@PostConstruct
	public void init() {
	}

	public List<EmployeeDetails> getEmployeeDetails(String personId)
			throws IllegalArgumentException, IllegalAccessException {
		ClientResponse cRes = getWebData(getEmployeeUrl(personId));
		if (cRes.getStatus() != HttpStatus.OK.value()) {
			throw new InternalError("Error occured fetching the details. Kindly try after some time", null);
		}

		// Parse the response
		String output = cRes.getEntity(String.class);
		return parseEmployeeDetailsResponse(output);
	}

	private static List<EmployeeDetails> parseEmployeeDetailsResponse(String output)
			throws IllegalArgumentException, IllegalAccessException {
		List<EmployeeDetails> results = new ArrayList<>();
		if (StringUtils.isEmpty(output)) {
			return results;
		}

		// Re-validate the status
		JSONObject jsonObject = new JSONObject(output);
		int status = jsonObject.getInt("status");
		if (status != HttpStatus.OK.value()) {
			throw new InternalError("Error occured fetching the details. Kindly try after some time", null);
		}

		JSONObject dataObject = jsonObject.getJSONObject("data");
		JSONArray employeeData = dataObject.getJSONArray("data");

		System.out.println("Output:" + employeeData.toString());
		if (employeeData.length() > 0) {
			for (int i = 0; i < employeeData.length(); i++) {
				JSONObject jsonEmployeeEntry = employeeData.getJSONObject(i);
				EmployeeDetails employeeInfo = gson.fromJson(jsonEmployeeEntry.toString(), EmployeeDetails.class);
				employeeInfo.processNavigationUrls(jsonEmployeeEntry);
				results.add(employeeInfo);
			}
		}
		return results;
	}

	private static String getEmployeeUrl(String personId) {
		return GET_PEOPLE_API_URL + "/" + personId + "/employments";
	}

}
