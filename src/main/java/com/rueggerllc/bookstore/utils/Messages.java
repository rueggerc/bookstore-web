package com.rueggerllc.bookstore.utils;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {
	
	public static FacesMessage getMessage(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		return getMessage(context,key);
	
	}
	
	public static FacesMessage getMessage(FacesContext context, String key) {
		ResourceBundle bundle = context.getApplication().getResourceBundle(context,"msg");
		String message = bundle.getString(key);
		FacesMessage msg = new FacesMessage(message,message);
		return msg;
	}


}
