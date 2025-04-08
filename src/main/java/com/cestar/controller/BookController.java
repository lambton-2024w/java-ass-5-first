package com.cestar.controller;


import java.util.ArrayList;
import java.util.List;

import com.cestar.beans.Book;
import com.cestar.dao.BookDao;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("books")
public class BookController {
	private BookDao bookDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getBooks() {
//		return new ArrayList<Book>();
		return bookDao.getBooks();
	}

	@POST
	public Book addBook(Book book) {
		return bookDao.saveBook(book);
	}
}
