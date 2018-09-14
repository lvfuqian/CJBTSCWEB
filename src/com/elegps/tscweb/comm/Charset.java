package com.elegps.tscweb.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.RequestProcessor;

public class Charset extends RequestProcessor {
	public Charset() {
	}

	public boolean processPreprocess(HttpServletRequest servletRequest,
			HttpServletResponse serveltResponse) {
		try {
			servletRequest.setCharacterEncoding("utf-8");
			System.out.println("请求被处理.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

}
