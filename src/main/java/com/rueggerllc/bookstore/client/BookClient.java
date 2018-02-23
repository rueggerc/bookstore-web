package com.rueggerllc.bookstore.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rueggerllc.bookstore.beans.Book;
import com.rueggerllc.bookstore.managedbeans.BooksBean;

public class BookClient {
	private static Logger logger = Logger.getLogger(BooksBean.class);
	private CloseableHttpClient httpClient;
	private String endpointTemplate = "http://localhost:8080/restful-jersey/rest/books/%s/";
	
	public List<Book> getBooks() throws Exception {
		try {
			logger.info("BookClient.getBooks() BEGIN");
			httpClient = HttpClients.custom().build();
			String endPoint = String.format(endpointTemplate, "books");
			HttpGet getRequest = new HttpGet(endPoint);
			
			// Headers
			getRequest.addHeader("accept", "application/json");
			
			// Execute
			CloseableHttpResponse httpClientResponse = httpClient.execute(getRequest);
			int httpStatusCode = httpClientResponse.getStatusLine().getStatusCode();
			
			List<Book> books = null;
			if (httpStatusCode != 200) {
				logger.error("ERROR Status Code=" + httpStatusCode);
			} else {
				String jsonResult = new Scanner(httpClientResponse.getEntity().getContent()).useDelimiter("\\A").next();
				logger.info("JSON=\n" + jsonResult);
				books = getResponse(jsonResult);
				// books = new ArrayList<Book>();
			}
			
			// Done
			httpClient.close();
			return books;
		} catch (Exception e) {
			logger.error("Error in GetBooks", e);
			throw e;
		}
	}
	
	private List<Book> getResponse(String jsonResult) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<Book> books = mapper.readValue(jsonResult, mapper.getTypeFactory().constructCollectionType(List.class, Book.class));
		return books;
	}
	
	
	
}
