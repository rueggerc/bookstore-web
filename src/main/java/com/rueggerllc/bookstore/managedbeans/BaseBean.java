package com.rueggerllc.bookstore.managedbeans;

import java.io.Serializable;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.rueggerllc.bookstore.utils.ErrorUtils;
import com.rueggerllc.bookstore.utils.Messages;


@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class BaseBean implements Serializable {

	private static Logger logger = Logger.getLogger(BaseBean.class);
	
	public void setRequestResultState(String requestResulState) {
		// assignment from outside system will be ignored
	}
	
	public String getRequestResultState() {
		String resultStateValue = ErrorUtils.getRequestResultState(getSession()).getValue();
		return resultStateValue;
	}
	
	protected Object getRequestAttribute(String key) {
		if (getRequestAttributes() == null) {
			logger.info("REQUEST ATTRIBUTES ARE NULL, returning NULL");
			return null;
		}
		return getRequestAttributes().get(key);
	}
	
	protected Object getRequestParameter(String key) {
		if (getRequestParameters() == null) {
			return null;
		}
		return getRequestParameters().get(key);
	}
	
	protected HttpSession getSession() {
		return (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	protected HttpServletRequest getRequest() {
		return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	protected HttpServletResponse getResponse() {
		return (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}
	
	protected void setSessionAttribute(String key, Object value) {
		if (getSessionMap() == null) {
			logger.info("Session Map could not be retrieved");
			return;
		}
		getSessionMap().put(key, value);
	}
	
	protected Object getSessionAttribute(String key) {
		if (getSessionMap() == null) {
			return null;
		}
		return getSessionMap().get(key);
	}
	
	protected Object getHeader(String key) {
		if (getRequestHeaders() == null) {
			return null;
		}
		return getRequestHeaders().get(key);
	}
	
	
	protected Map<String,Object> getSessionMap() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String,Object> sessionMap = externalContext.getSessionMap();
		return sessionMap;
	}
	
	protected Map<String,Object> getRequestAttributes() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		return externalContext.getRequestMap();
	}
	
	protected Map<String,String> getRequestParameters() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		return externalContext.getRequestParameterMap();
	}
	
	protected Map<String,String> getRequestHeaders() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		return externalContext.getRequestHeaderMap();
	}
	
	protected void addError(String fieldId, String errorKey) {
		logger.info("Adding Error Message: " + fieldId);
		FacesMessage message = Messages.getMessage(errorKey);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(fieldId, message);
	}
	
	protected void addLiteralError(String fieldId, String errorMsg) {
		logger.info("Adding Error Message: " + fieldId);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg, null);
		FacesContext.getCurrentInstance().addMessage(fieldId, message);
	}
	
	protected void addGlobalError(String msgKey) {
		logger.info("Adding Global Error Message");
		FacesMessage message = Messages.getMessage(msgKey);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	protected void addLiteralGlobalError(String errorMsg) {
		logger.info("Adding Global Error Message");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	
	protected void addGlobalMessage(String msgKey) {
		logger.info("Adding Global Error Message");
		FacesMessage message = Messages.getMessage(msgKey);
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	protected void addLiteralGlobalMessage(String infoMsg) {
		logger.info("Adding Global Error Message");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, infoMsg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	

	


}
