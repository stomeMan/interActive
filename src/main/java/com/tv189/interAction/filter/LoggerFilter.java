package com.tv189.interAction.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class LoggerFilter implements Filter{
	
	public static Logger logger = Logger.getLogger("logger");
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		String logInfo = "Request INFO="+request.getRequestURI()+"?"+request.getQueryString();
		String add = " Request Address = "+request.getRemoteAddr();
		String host = " Request Host = "+request.getRemoteHost();
		String port = " Request Port = "+request.getRemotePort();
		logger.info(add + host + port);
		logger.info(logInfo);
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
