package com.rueggerllc.bookstore.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import com.rueggerllc.bookstore.beans.Book;
import com.rueggerllc.bookstore.client.BookClient;


@ManagedBean(name="booksBean")
@SessionScoped
@SuppressWarnings("serial")
public class BooksBean extends BaseBean {

	private static Logger logger = Logger.getLogger(BooksBean.class);
	
	@PostConstruct
	private void init() {
	}
	
	public String getInitialView() throws Exception {
		logger.info("BooksBean.getInitialView()");
		return null;
	}
	
	public List<Book> getBooks() throws Exception {
		try {
			logger.info("getBooks BEGIN");
			BookClient client = new BookClient();
			List<Book> books = client.getBooks();
			return books;
		} catch (Exception e) {
			logger.error("ERROR", e);
			throw e;
		}
	}
	

}
