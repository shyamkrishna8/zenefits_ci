package com.shyam.zenefits.ci.utils;

import org.json.JSONObject;
import org.springframework.util.StringUtils;

public class ZenefitsUtils {

	public static String getUrlFromResponseObject(JSONObject jsonObject, String field) {
		if (!StringUtils.isEmpty(field) && jsonObject.has(field)) {
			JSONObject fieldEntry = jsonObject.getJSONObject(field);
			if (fieldEntry.has(Constants.URL_SLUG) && !fieldEntry.isNull(Constants.URL_SLUG)) {
				return fieldEntry.getString(Constants.URL_SLUG);
			}
		}

		return null;
	}
}
