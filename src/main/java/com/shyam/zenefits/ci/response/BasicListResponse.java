package com.shyam.zenefits.ci.response;

public class BasicListResponse extends BasicResponse {
	private String nextUrlStartsWithId;

	public BasicListResponse() {
		super();
	}

	public String getNextUrlStartsWithId() {
		return nextUrlStartsWithId;
	}

	public void setNextUrlStartsWithId(String nextUrlStartsWithId) {
		this.nextUrlStartsWithId = nextUrlStartsWithId;
	}

	@Override
	public String toString() {
		return "BasicListResponse [nextUrlStartsWithId=" + nextUrlStartsWithId + ", toString()=" + super.toString()
				+ "]";
	}
}
