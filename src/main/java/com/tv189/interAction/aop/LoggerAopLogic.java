package com.tv189.interAction.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggerAopLogic {
	private static final Logger logger = LoggerFactory.getLogger(LoggerAopLogic.class);
	
	@Pointcut("execution(* com.tv189.interAction.controller.*.*(..))")
	public void aspect(){
		
	}
	
	@Before("aspect()")
	public void doBefore(JoinPoint joinPoint){
		HttpServletRequest request = null;  
		Object args[] = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			 if (args[i] instanceof HttpServletRequest) {  
                 request = (HttpServletRequest) args[i];  
             }
		}
		
//		String add = "Request Address = ";
		String logInfo = "Request INFO=";
		if(request != null){
//			add += request.getRemoteAddr();
			logInfo += request.getRequestURI()+"?"+request.getQueryString();
		}
//		logger.info(add);
		logger.info(logInfo);
	}
	
	@After("aspect()")
	public void doAfter(JoinPoint joinPoint){
		
	}
	
}
