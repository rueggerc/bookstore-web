package com.rueggerllc.bookstore.utils;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.rueggerllc.bookstore.enums.RequestResultState;

public class ErrorUtils {
	
	private static Logger logger = Logger.getLogger(ErrorUtils.class);
	public static final String ERROR_PAGE = "errorPage.xhtml";
	public static final String ERROR_PAGE_ACTION = "errorPage";
	
	public static RequestResultState getRequestResultState(HttpSession session) {
		RequestResultState requestResultState = RequestResultState.fromString((String)session.getAttribute(RequestResultState.SESSION_KEY));
		
		if (requestResultState.equals(RequestResultState.UNKNOWN)) {
			requestResultState = RequestResultState.PASS;
			session.setAttribute(RequestResultState.SESSION_KEY, requestResultState.getValue());
		}
		return requestResultState;
	}
	
	public static void setRequestResultFailState(HttpSession session) {
		session.setAttribute(RequestResultState.SESSION_KEY, RequestResultState.FAIL.getValue());
	}
	
	public static void setRequestResultPassState(HttpSession session) {
		session.setAttribute(RequestResultState.SESSION_KEY, RequestResultState.PASS.getValue());
	}
	 
	public static void setCurrentErrorMessageName(HttpSession session, String messageName) {
		session.setAttribute(Constants.ERROR_PAGE_MESSAGE_KEY, messageName);
	}
	
	public static String getCurrentErrorMessageName(HttpServletRequest request) {
		String errorMessageName = (String)((request.getSession()).getAttribute(Constants.ERROR_PAGE_MESSAGE_KEY));
		if (errorMessageName == null) {
			Object errorMessageParam = ((request.getParameterMap()).get(Constants.ERROR_PAGE_MESSAGE_KEY));
			if (errorMessageParam != null) {
				if (errorMessageParam.getClass().equals(String.class)) {
					errorMessageName = (String)errorMessageParam;
				} else if (errorMessageParam.getClass().equals(String[].class)) {
					errorMessageName = ((String[])errorMessageParam)[0];
				}
				
			} else {
				logger.info("No error Message was set");
			}
		}
		return errorMessageName;
	}
	
	public static String getCurrentErrorMessage(HttpServletRequest request) {
		String errorMessage = "An unexpected error has occurred";
		String errorMessageName = getCurrentErrorMessageName(request);
		if (errorMessageName != null) {
			FacesMessage message = Messages.getMessage(errorMessageName);
			errorMessage = message.getDetail();
			if (errorMessage == null) {
				logger.error("Message [" + errorMessageName + "] was not configured");
				errorMessage = "An undefined error [" + errorMessageName + "] occurred";
			}
		}
		return errorMessage;
	}
	
	public static void raiseErrorState(HttpServletRequest request, HttpServletResponse response, String errorMessageName) {
		logger.info("RAISE ERROR STATE");
		ErrorUtils.setRequestResultFailState(request.getSession());
		ErrorUtils.setCurrentErrorMessageName(request.getSession(), errorMessageName);
		if (isAjaxRequest(request) == false) {
			logger.info("NOT AJAX");
			try {
				response.sendRedirect(ErrorUtils.getErrorRedirectUrl(request,true));
			} catch (IOException e) {
				logger.info("Redirecting to error page threw Exception");
			}
		} else {
			logger.info("was AJAX Request");
		}
		
	}
	
	private static boolean isAjaxRequest(HttpServletRequest request) {
		logger.info("checking ajax");
		String requestTypeHeader = request.getHeader("Faces-Request");
		return (requestTypeHeader != null && requestTypeHeader.equals("partial/ajax"));
	}
	
	public static String getErrorRedirectUrl(HttpServletRequest request, boolean addCurrentErrorArguments) {
		String url = ErrorUtils.ERROR_PAGE;
		if (addCurrentErrorArguments) {
			url = getErrorRedirectUrlWithArguments(getCurrentErrorMessageName(request));
		}
		return url;
	}
	
	public static String getErrorRedirectUrlWithArguments(String errorMessageName) {
		return ErrorUtils.ERROR_PAGE + "?" + Constants.ERROR_PAGE_MESSAGE_KEY + "=" + errorMessageName;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
