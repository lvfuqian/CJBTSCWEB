package com.elegps.tscweb.model.hstmodel;

import java.util.List;

public class TbTyphoonListData {
	private String base;
	private String error_code;
	private String reason;
	private List<TbTyphoonList> result;

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<TbTyphoonList> getResult() {
		return result;
	}

	public void setResult(List<TbTyphoonList> result) {
		this.result = result;
	}

}