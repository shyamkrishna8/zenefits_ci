package com.shyam.zenefits.ci.connector;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shyam.zenefits.ci.pojo.PersonInfo;

@Service
public class PeopleConnector extends AbstractZenefitsConnector {

	private static final String GET_PEOPLE_API_URL = API_BASE_URL + "/core/companies"; // https://api.zenefits.com/core/companies/131492/people

	@PostConstruct
	public void init() {
	}

	public List<PersonInfo> getCompanyPeople(String companyId) throws Exception {
		// Parse the response
		String output = getWebData(getPeopleUrl(companyId));
		return parsePeopleResponse(output);
	}

	private static List<PersonInfo> parsePeopleResponse(String output)
			throws IllegalArgumentException, IllegalAccessException {
		List<PersonInfo> results = new ArrayList<>();
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
		JSONArray personData = dataObject.getJSONArray("data");

		System.out.println("Output:" + personData.toString());
		if (personData.length() > 0) {
			for (int i = 0; i < personData.length(); i++) {
				JSONObject jsonPersonEntry = personData.getJSONObject(i);
				PersonInfo personInfo = gson.fromJson(jsonPersonEntry.toString(), PersonInfo.class);
				personInfo.processNavigationUrls(jsonPersonEntry);
				results.add(personInfo);
			}
		}
		return results;
	}

	private static String getPeopleUrl(String companyId) {
		return GET_PEOPLE_API_URL + "/" + companyId + "/people";
	}
}
