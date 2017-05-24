package com.shyam.zenefits.ci.utils;

import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpMethod;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class WebUtils {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	public static final WebUtils INSTANCE = new WebUtils();

	private Client client = Client.create();

	public ClientResponse sendWebData(String location, String body, boolean isJsonReq, String authorizationParam, HttpMethod httpMethod) {
		WebResource webResource = client.resource(location);
		client.setReadTimeout(60000);

//		if (!StringUtils.isEmpty(authorizationParam)) {
//			webResource.aheader(AUTHORIZATION_HEADER, authorizationParam);
//		}

		ClientResponse response = null;
		switch (httpMethod) {
		case POST:
			response = webResource.type(MediaType.APPLICATION_JSON).header(AUTHORIZATION_HEADER, authorizationParam).post(ClientResponse.class, body);
			break;
		case PUT:
			response = webResource.type(MediaType.APPLICATION_JSON).header(AUTHORIZATION_HEADER, authorizationParam).put(ClientResponse.class, body);
			break;
		case GET:
			response = webResource.header(AUTHORIZATION_HEADER, authorizationParam).get(ClientResponse.class);
			break;
		case DELETE:
			response = webResource.type(MediaType.APPLICATION_JSON).header(AUTHORIZATION_HEADER, authorizationParam).delete(ClientResponse.class, body);
			break;
		default:
			break;
		}
		return response;
	}
}
