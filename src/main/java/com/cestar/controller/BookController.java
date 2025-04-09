package com.cestar.controller;


import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cestar.beans.Book;
import com.cestar.dao.BookDao;



@RestController
@RequestMapping(value="/books")
public class BookController {
	private BookDao bookDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@RequestMapping(value="/", method=RequestMethod.GET, produces = "application/json")
	public List<Book>  getBooks() {
		List<Book> books =  bookDao.getBooks();
		return books;
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public Book addBook(Book book) {
		return bookDao.saveBook(book);
	}
}
