package com.shyam.zenefits.ci.requests;

import com.shyam.zenefits.ci.utils.Constants;

public abstract class AbstractListRequest {
	private Integer starting_after;
	private Integer ending_before;
	private Integer limit = Constants.DEFAULT_LIMIT;

	public AbstractListRequest() {
		super();
	}

	public Integer getStarting_after() {
		return starting_after;
	}

	public void setStarting_after(Integer starting_after) {
		this.starting_after = starting_after;
	}

	public Integer getEnding_before() {
		return ending_before;
	}

	public void setEnding_before(Integer ending_before) {
		this.ending_before = ending_before;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "ListRequest [starting_after=" + starting_after + ", ending_before=" + ending_before + ", limit=" + limit
				+ ", toString()=" + super.toString() + "]";
	}
}
