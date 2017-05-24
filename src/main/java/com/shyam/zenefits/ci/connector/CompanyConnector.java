package com.shyam.zenefits.ci.connector;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shyam.zenefits.ci.pojo.CompanyBankAccount;
import com.shyam.zenefits.ci.pojo.CompanyBasicInfo;

@Service
public class CompanyConnector extends AbstractZenefitsConnector {

	private static final String GET_COMPANIES_BASE_API_URL = API_BASE_URL + "/core/companies"; //https://api.zenefits.com/

	private static Gson gson = new Gson();
	private static Type COMPANY_INFO_LIST_TYPE = new TypeToken<List<CompanyBasicInfo>>() {}.getType();

	@PostConstruct
	public void init() {
	}

	public List<CompanyBasicInfo> getCoreCompanies() throws Exception {
		String output = getWebData(getCompanyUrl());
		return parseCompanyInfo(output);
	}

	public List<CompanyBankAccount> getCompanyBankAccount(String companyId) throws Exception {
		// Parse the response
		String output = getWebData(getPeopleUrl(companyId));
		return parseCompanyBankAccountResponse(output);
	}

	private static List<CompanyBasicInfo> parseCompanyInfo(String output) {
		List<CompanyBasicInfo> companyInfos = new ArrayList<>();
		if (StringUtils.isEmpty(output)) {
			return companyInfos;
		}

		// Re-validate the status
		JSONObject jsonObject = new JSONObject(output);
		int status = jsonObject.getInt("status");
		if (status != HttpStatus.OK.value()) {
			throw new InternalError("Error occured fetching the details. Kindly try after some time", null);
		}

		JSONObject dataObject = jsonObject.getJSONObject("data");
		JSONArray companyInfo = dataObject.getJSONArray("data");

		System.out.println("Output:" + companyInfo.toString());
		return gson.fromJson(companyInfo.toString(), COMPANY_INFO_LIST_TYPE);
	}

	private static List<CompanyBankAccount> parseCompanyBankAccountResponse(String output)
			throws IllegalArgumentException, IllegalAccessException {
		List<CompanyBankAccount> results = new ArrayList<>();
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
		JSONArray companyBankAccountData = dataObject.getJSONArray("data");

		System.out.println("Output:" + companyBankAccountData.toString());
		if (companyBankAccountData.length() > 0) {
			for (int i = 0; i < companyBankAccountData.length(); i++) {
				JSONObject jsonCompanyBankAccountEntry = companyBankAccountData.getJSONObject(i);
				CompanyBankAccount companyBankAccount = gson.fromJson(jsonCompanyBankAccountEntry.toString(),
						CompanyBankAccount.class);
				companyBankAccount.processNavigationUrls(jsonCompanyBankAccountEntry);
				results.add(companyBankAccount);
			}
		}
		return results;
	}

	private static String getCompanyUrl() {
		return GET_COMPANIES_BASE_API_URL;
	}

	private static String getPeopleUrl(String companyId) {
		return GET_COMPANIES_BASE_API_URL + "/" + companyId + "/company_banks";
	}
}
