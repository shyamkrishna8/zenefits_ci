package com.shyam.zenefits.ci.pojo;

import org.json.JSONObject;

public abstract class AbstractZenifitsEntity {
	private String id;
	private String url;
	private String object;

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

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "AbstractZenifitsEntity [id=" + id + ", url=" + url + ", object=" + object + ", toString()="
				+ super.toString() + "]";
	}
}
