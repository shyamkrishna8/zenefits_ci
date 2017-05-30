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

import com.google.gson.reflect.TypeToken;
import com.shyam.zenefits.ci.exceptions.InternalServerException;
import com.shyam.zenefits.ci.exceptions.ZException;
import com.shyam.zenefits.ci.pojo.CompanyBankAccount;
import com.shyam.zenefits.ci.pojo.CompanyBasicInfo;
import com.shyam.zenefits.ci.pojo.DepartmentInfo;
import com.shyam.zenefits.ci.pojo.LocationInfo;

@Service
public class CompanyConnector extends AbstractZenefitsConnector {

	private static final String GET_COMPANIES_BASE_API_URL = API_BASE_URL + "/core/companies"; // https://api.zenefits.com/

	private static Type COMPANY_INFO_LIST_TYPE = new TypeToken<List<CompanyBasicInfo>>() {
	}.getType();

	@PostConstruct
	public void init() {
	}

	public List<CompanyBasicInfo> getCoreCompanies() throws ZException {
		String output = getWebData(getCompanyUrl());
		return parseCompanyInfo(output);
	}

	public List<CompanyBankAccount> getCompanyBankAccount(String companyId) throws ZException {
		// Parse the response
		String output = getWebData(getCompanyBankUrl(companyId));
		return parseCompanyBankAccountResponse(output);
	}

	public List<DepartmentInfo> getCompanyDepartments(String companyId) throws ZException {
		// Parse the response
		String output = getWebData(getCompanyDepartmentsUrl(companyId));
		return parseCompanyDepartmentResponse(output);
	}

	public List<LocationInfo> getCompanyLocations(String companyId) throws ZException {
		// Parse the response
		String output = getWebData(getCompanyLocationsUrl(companyId));
		return parseCompanyLocationResponse(output);
	}

	private static List<CompanyBasicInfo> parseCompanyInfo(String output) throws InternalServerException {
		List<CompanyBasicInfo> companyInfos = new ArrayList<>();
		if (StringUtils.isEmpty(output)) {
			return companyInfos;
		}

		// Re-validate the status
		JSONObject jsonObject = new JSONObject(output);
		int status = jsonObject.getInt("status");
		if (status != HttpStatus.OK.value()) {
			throw new InternalServerException("Error occured fetching the details. Kindly try after some time");
		}

		JSONObject dataObject = jsonObject.getJSONObject("data");
		JSONArray companyInfo = dataObject.getJSONArray("data");

		System.out.println("Output:" + companyInfo.toString());
		return gson.fromJson(companyInfo.toString(), COMPANY_INFO_LIST_TYPE);
	}

	private static List<CompanyBankAccount> parseCompanyBankAccountResponse(String output)
			throws InternalServerException {
		List<CompanyBankAccount> results = new ArrayList<>();
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
		JSONArray companyBankAccountData = dataObject.getJSONArray("data");

		System.out.println("Output:" + companyBankAccountData.toString());
		try {
			if (companyBankAccountData.length() > 0) {
				for (int i = 0; i < companyBankAccountData.length(); i++) {
					JSONObject jsonCompanyBankAccountEntry = companyBankAccountData.getJSONObject(i);
					CompanyBankAccount companyBankAccount = gson.fromJson(jsonCompanyBankAccountEntry.toString(),
							CompanyBankAccount.class);
					companyBankAccount.processNavigationUrls(jsonCompanyBankAccountEntry);
					results.add(companyBankAccount);
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException ex) {
			throw new InternalServerException("Error parsing the response urls ex:" + ex.getMessage());
		}
		return results;
	}

	private static List<LocationInfo> parseCompanyLocationResponse(String output) throws InternalServerException {
		List<LocationInfo> results = new ArrayList<>();
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
		JSONArray companylocationInfoData = dataObject.getJSONArray("data");

		System.out.println("Output:" + companylocationInfoData.toString());
		try {
			if (companylocationInfoData.length() > 0) {
				for (int i = 0; i < companylocationInfoData.length(); i++) {
					JSONObject jsonCompanyLocationEntry = companylocationInfoData.getJSONObject(i);
					LocationInfo companyLocationInfo = gson.fromJson(jsonCompanyLocationEntry.toString(),
							LocationInfo.class);
					companyLocationInfo.processNavigationUrls(jsonCompanyLocationEntry);
					results.add(companyLocationInfo);
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException ex) {
			throw new InternalServerException("Error parsing the response urls ex:" + ex.getMessage());
		}
		return results;
	}

	private static List<DepartmentInfo> parseCompanyDepartmentResponse(String output)
			throws InternalServerException {
		List<DepartmentInfo> results = new ArrayList<>();
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
		JSONArray companyDepartmentData = dataObject.getJSONArray("data");

		System.out.println("Output:" + companyDepartmentData.toString());
		try {
			if (companyDepartmentData.length() > 0) {
				for (int i = 0; i < companyDepartmentData.length(); i++) {
					JSONObject jsonCompanyDepartmentEntry = companyDepartmentData.getJSONObject(i);
					DepartmentInfo companyDepartmentAccount = gson.fromJson(jsonCompanyDepartmentEntry.toString(),
							DepartmentInfo.class);
					companyDepartmentAccount.processNavigationUrls(jsonCompanyDepartmentEntry);
					results.add(companyDepartmentAccount);
				}
			}
			return results;
		} catch (IllegalArgumentException | IllegalAccessException ex) {
			throw new InternalServerException("Error parsing the response urls ex:" + ex.getMessage());
		}
	}

	private static String getCompanyUrl() {
		return GET_COMPANIES_BASE_API_URL;
	}

	private static String getCompanyBankUrl(String companyId) {
		return GET_COMPANIES_BASE_API_URL + "/" + companyId + "/company_banks";
	}

	private static String getCompanyDepartmentsUrl(String companyId) {
		return GET_COMPANIES_BASE_API_URL + "/" + companyId + "/departments";
	}

	private static String getCompanyLocationsUrl(String companyId) {
		return GET_COMPANIES_BASE_API_URL + "/" + companyId + "/locations";
	}

}
