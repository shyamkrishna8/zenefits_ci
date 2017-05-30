package com.shyam.zenefits.ci.connector;

import org.springframework.http.HttpMethod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shyam.zenefits.ci.exceptions.InternalServerException;
import com.shyam.zenefits.ci.exceptions.NotFoundException;
import com.shyam.zenefits.ci.exceptions.ZException;
import com.shyam.zenefits.ci.utils.ConfigUtils;
import com.shyam.zenefits.ci.utils.WebUtils;
import com.sun.jersey.api.client.ClientResponse;

public abstract class AbstractZenefitsConnector {

	private static final String BEARER = "Bearer";
	private static final String APPI_ID = ConfigUtils.INSTANCE.getStringValue("zenefits.ci.access.token");
	protected static Gson gson = new GsonBuilder()
			.addDeserializationExclusionStrategy(new DeserializationExclusionStrategy()).setPrettyPrinting().create();

	protected static final String API_BASE_URL = ConfigUtils.INSTANCE.getStringValue("base.api.url"); // "https://api.zenefits.com";

	public String getWebData(String location) throws ZException {
		ClientResponse cRes = getWebData(location, true);
		String output = cRes.getEntity(String.class);
		switch (cRes.getStatus()) {
		case 200:
			break;
		case 404:
			throw new NotFoundException("Entity not found. Kindly recheck. Output:" + output);
		case 401:
			throw new InternalServerException("Integration token expired. Kindly contact the developer");
		default:
			throw new InternalServerException("Error occured fetching the details. Kindly try after some time");
		}

		return output;
	}

	public ClientResponse getWebData(String location, boolean isJsonReq) {
		return WebUtils.INSTANCE.sendWebData(location, null, isJsonReq, getAuthorizationParam(), HttpMethod.GET);
	}

	public ClientResponse sendWebData(String location, String body, boolean isJsonReq, HttpMethod httpMethod) {
		return WebUtils.INSTANCE.sendWebData(location, body, isJsonReq, getAuthorizationParam(), httpMethod);
	}

	public static String getAuthorizationParam() {
		return BEARER + " " + APPI_ID;
	}
}
