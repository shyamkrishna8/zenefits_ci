package com.shyam.zenefits.ci.pojo;

import org.json.JSONObject;

public abstract class AbstractZenifitsEntity {
	private String id;
	private String url;

	public abstract void processNavigationUrls(JSONObject responseObject)
			throws IllegalArgumentException, IllegalAccessException;

	public AbstractZenifitsEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "AbstractZenifitsEntity [id=" + id + ", url=" + url + ", toString()=" + super.toString() + "]";
	}
}
