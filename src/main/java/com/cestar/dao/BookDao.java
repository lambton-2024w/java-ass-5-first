package com.cestar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cestar.beans.Book;

public class BookDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<Book> getBooks() {
		String sqlQuery = "SELECT * FROM book";
		List<Book> books = this.template.query(sqlQuery, new RowMapper<Book>() {
			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setGenre(rs.getString("genre"));
				book.setPrice(rs.getDouble("price"));
				return book;
			}
		});
		if (books == null) {
			return new ArrayList<Book>();
		}
		return books;
	}

	public Book saveBook(Book book) {
		String sqlInsert = "INSERT INTO book (id, name, author, price, genre) + VALUES (?, ?, ?, ?, ?)";
		int status = template.update(sqlInsert, book.getId(), book.getName(), book.getAuthor(), book.getPrice(),
				book.getGenre());
		if (status > 0)
			return book;
		else
			return null;
	}

}
