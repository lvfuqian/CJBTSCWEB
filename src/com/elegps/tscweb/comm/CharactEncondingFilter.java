package com.elegps.tscweb.comm;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharactEncondingFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		  HttpServletResponse response = (HttpServletResponse) resp;
		  
		  if(request.getMethod().equalsIgnoreCase("POST")){
			  request.setCharacterEncoding("UTF-8");
			  response.setCharacterEncoding("UTF-8");
		  }else{
//			  Enumeration enums = request.getParameterNames();
//			  while(enums.hasMoreElements()){
//				  String name = enums.nextElement().toString();
//				  String[] value = request.getParameterValues(name);
//				  for (int i = 0; i < value.length; i++) {
//					  value[i] = new String(value[i].getBytes("ISO-8859-1"), "UTF-8");
//				}
//			   }
			  
		  }
			  chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
