package com.shyam.zenefits.ci.utils;

import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class WebUtils {

	public static final WebUtils INSTANCE = new WebUtils();

	private WebUtils() {
		super();
	}

	private Client client = Client.create();

	public ClientResponse sendWebData(String location, String body, boolean isJsonReq, String authorizationParam,
			HttpMethod httpMethod) {
		WebResource webResource = client.resource(location);
		client.setReadTimeout(60000);

		// if (!StringUtils.isEmpty(authorizationParam)) {
		// webResource.aheader(AUTHORIZATION_HEADER, authorizationParam);
		// }

		ClientResponse response = null;
		switch (httpMethod) {
		case POST:
			response = webResource.type(MediaType.APPLICATION_JSON)
					.header(Constants.AUTHORIZATION_HEADER, authorizationParam).post(ClientResponse.class, body);
			break;
		case PUT:
			response = webResource.type(MediaType.APPLICATION_JSON)
					.header(Constants.AUTHORIZATION_HEADER, authorizationParam).put(ClientResponse.class, body);
			break;
		case GET:
			response = webResource.header(Constants.AUTHORIZATION_HEADER, authorizationParam).get(ClientResponse.class);
			break;
		case DELETE:
			response = webResource.type(MediaType.APPLICATION_JSON)
					.header(Constants.AUTHORIZATION_HEADER, authorizationParam).delete(ClientResponse.class, body);
			break;
		default:
			break;
		}
		return response;
	}

	// Will not work for list fields
	public static String createQueryStringOfObject(Object object) throws IllegalArgumentException, IllegalAccessException {
		String queryString = "";
		Class<?> queryClazz = object.getClass();
		while (queryClazz != null) {
			Field[] fields = queryClazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Object valueObject = field.get(object);
				if (valueObject == null) {
					continue;
				}
				if (valueObject != null) {
					queryString += "&" + field.getName() + "=" + getUrlEncodedValue(String.valueOf(valueObject));
				}
			}

			queryClazz = queryClazz.getSuperclass();
		}

		return queryString;
	}

	public static String getUrlEncodedValue(String value) {
		String returnValue = value;
		try {
			returnValue = URLEncoder.encode(value, StandardCharsets.UTF_8.name());
		} catch (Exception ex) {
		}
		return returnValue;
	}

	public static String getQueryParamFromUrl(String url, String fieldName) throws URISyntaxException {
		String value = null;
		if (!StringUtils.isEmpty(url) && !StringUtils.isEmpty(fieldName)) {
			List<NameValuePair> paramList = new URIBuilder(url).getQueryParams();
			if (!CollectionUtils.isEmpty(paramList)) {
				for (NameValuePair entry : paramList) {
					if (fieldName.equals(entry.getName())) {
						value = entry.getValue();
						break;
					}
				}
			}
		}

		return value;
	}

	public static String appendQueryString(String url, String queryString) {
		if (url.contains("?")) {
			return url += "&" + queryString;
		} else {
			return url += '?' + queryString;
		}
	}
}
