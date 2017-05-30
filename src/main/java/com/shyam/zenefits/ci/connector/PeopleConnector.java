package com.shyam.zenefits.ci.connector;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shyam.zenefits.ci.exceptions.ErrorCode;
import com.shyam.zenefits.ci.exceptions.InternalServerException;
import com.shyam.zenefits.ci.exceptions.ZException;
import com.shyam.zenefits.ci.pojo.PersonInfo;
import com.shyam.zenefits.ci.requests.GetPeoplesReq;
import com.shyam.zenefits.ci.response.BasicListResponse;
import com.shyam.zenefits.ci.utils.Constants;
import com.shyam.zenefits.ci.utils.WebUtils;

@Service
public class PeopleConnector extends AbstractZenefitsConnector {

	private static final String GET_PEOPLE_API_URL = API_BASE_URL + "/core/companies"; // https://api.zenefits.com/core/companies/131492/people

	@PostConstruct
	public void init() {
	}

	public BasicListResponse getCompanyPeople(String companyId, GetPeoplesReq getPeoplesReq) throws ZException {
		try {
			String getpeopleUrl = getPeopleUrl(companyId, getPeoplesReq);
			String output = getWebData(getpeopleUrl);
			return parsePeopleResponse(output);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new InternalServerException("Error parsing the request parameters ex:" + e.getMessage());
		}
	}

	private static BasicListResponse parsePeopleResponse(String output) throws ZException {
		BasicListResponse response = new BasicListResponse();
		if (StringUtils.isEmpty(output)) {
			return response;
		}

		// Re-validate the status
		JSONObject jsonObject = new JSONObject(output);
		int status = jsonObject.getInt("status");
		if (status != HttpStatus.OK.value()) {
			throw new InternalError("Error occured fetching the details. Kindly try after some time", null);
		}

		JSONObject dataObject = jsonObject.getJSONObject("data");
		String nextUrl = "";
		if (dataObject.has("next_url") && !dataObject.isNull("next_url")) {
			System.out.println("dataobject:" + dataObject.toString());
			nextUrl = (String) dataObject.get("next_url");
		} else {
			System.out.println("dataobject next url is null or empty.");
		}

		JSONArray personData = dataObject.getJSONArray("data");

		System.out.println("Output:" + personData.toString());
		List<PersonInfo> results = new ArrayList<>();
		try {
			if (personData.length() > 0) {
				for (int i = 0; i < personData.length(); i++) {
					JSONObject jsonPersonEntry = personData.getJSONObject(i);
					PersonInfo personInfo = gson.fromJson(jsonPersonEntry.toString(), PersonInfo.class);
					personInfo.processNavigationUrls(jsonPersonEntry);
					results.add(personInfo);
				}
			}

			response.setResponse(results);
			String startingWith = WebUtils.getQueryParamFromUrl(nextUrl, Constants.STARTING_AFTER_SLUG);
			response.setNextUrlStartsWithId(startingWith);
			return response;
		} catch (IllegalArgumentException | IllegalAccessException ex) {
			throw new InternalServerException(ErrorCode.INTERNAL_PARSE_ERROR,
					"Error occured parsing the response urls.");
		} catch (URISyntaxException e) {
			throw new InternalServerException(ErrorCode.INTERNAL_PARSE_ERROR,
					"Error occured parsing the starts with urls.");
		}
	}

	private static String getPeopleUrl(String companyId, GetPeoplesReq getPeoplesReq)
			throws IllegalArgumentException, IllegalAccessException {
		String getPeoplesUrl = GET_PEOPLE_API_URL + "/" + companyId + "/people";
		String queryString = WebUtils.createQueryStringOfObject(getPeoplesReq);
		if (!StringUtils.isEmpty(queryString)) {
			getPeoplesUrl = WebUtils.appendQueryString(getPeoplesUrl, queryString);
		}

		return getPeoplesUrl;
	}
}
