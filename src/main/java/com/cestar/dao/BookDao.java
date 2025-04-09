package com.cestar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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
		String sqlInsert = "INSERT INTO book (id, name, author, price, genre) VALUES (?, ?, ?, ?, ?)";
		int status = template.update(sqlInsert, book.getId(), book.getName(), book.getAuthor(), book.getPrice(),
				book.getGenre());
		return status > 0 ? book : null;
	}

	public Book getBookById(int bookId) {
	    String sql = "SELECT * FROM book WHERE id = ?";
	    
	    try {
	        return template.queryForObject(
	            sql,
	            (rs, rowNum) -> new Book(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getString("author"),
	                rs.getDouble("price"),
	                rs.getString("genre")
	            ),
	            bookId
	        );
	    } catch (EmptyResultDataAccessException e) {
	        // Return null if no book found with this ID
	        return null;
	    }
	}
	
	public Book updateBook(int bookId, Book book) {
		try {
	        String sqlUpdate = "UPDATE book SET name = ?, author = ?, price = ?, genre = ? WHERE id = ?";
	        int status = template.update(sqlUpdate, book.getName(), book.getAuthor(), book.getPrice(), book.getGenre(), bookId);

	        if (status > 0) {
	            // Set the correct ID in the book object
	            book.setId(bookId);
	            return book;
	        } else {
	            return null;
	        }
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	public Boolean deleteBook(int bookId) {
		try {
			// delete the book 
			String sqlDelete = "DELETE FROM book WHERE id = ?";
			int rowsAffected = template.update(sqlDelete, bookId);

			return rowsAffected > 0 ? true : false;

		} catch (EmptyResultDataAccessException e) {
			// Book not found
			return false;
		} catch (DataAccessException e) {
			// Other database errors
			e.printStackTrace();
			return false;
		}
	}

}
