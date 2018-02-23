package com.rueggerllc.bookstore.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class StartupListener implements ServletContextListener {
	
	private static Logger logger = Logger.getLogger(StartupListener.class);
	
	public StartupListener() {
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("Context Successfully Destroyed for bookstore-web");
	}
	
	public void contextInitialized(ServletContextEvent event) {
		logger.info("=== bookstore-web Context Initialization BEGIN ===");
		logger.info("=== bookstore-web Context Initialization END ===");
	}

}
