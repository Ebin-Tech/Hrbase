package com.aembase.test.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.aembase.test.common.BookConstants.BookType;
import com.aembase.test.common.Utilities;
import com.aembase.test.obj.BookObj;
import com.aembase.test.persistence.DBConnector;

public class BookDAO {
	private static final String SQL_INSERT_BOOK = "INSERT INTO tab_book(book_name, book_author,book_image,book_type,book_published_date) VALUES(?,?,?,?,?)";
	private static final String SQL_SELECT_ALL_BOOKS = "SELECT book_id, book_name, book_author,book_image,book_type,book_published_date FROM tab_book ORDER BY book_name";
	private static final String SQL_SELECT_BOOK = "SELECT book_id, book_name, book_author,book_image,book_type,book_published_date FROM tab_book WHERE book_id=?";
	private static final String SQL_UPDATE_BOOK_WITH_IMAGE = "UPDATE tab_book SET book_name=?, book_author=?,book_type=?,book_published_date=?,book_image=? WHERE book_id=?";
	private static final String SQL_UPDATE_BOOK_WITH_OUT_IMAGE = "UPDATE tab_book SET book_name=?, book_author=?,book_type=?,book_published_date=? WHERE book_id=?";
	private static final String SQL_DELETE_BOOK = "DELETE FROM tab_book WHERE book_id=?";

	public static void insertBook(BookObj bookObj) throws Exception {
		try {
			Connection connection = DBConnector.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_BOOK);

			preparedStatement.setString(1, bookObj.getBookName());
			preparedStatement.setString(2, bookObj.getBookAuthor());
			preparedStatement.setBytes(3, bookObj.getBookImage());
			preparedStatement.setInt(4, bookObj.getBookType().getValue());
			preparedStatement.setDate(5, new Date(bookObj.getBookPublishedDate().getTime()));
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static BookObj selectBook(int bookId) throws Exception {
		try {
			Connection connection = DBConnector.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BOOK);
			preparedStatement.setInt(1, bookId);
			ResultSet resultSet = preparedStatement.executeQuery();
			BookObj bookObj = null;

			if (resultSet.next()) {
				bookObj = new BookObj();
				bookObj.setBookId(resultSet.getInt("book_id"));
				bookObj.setBookName(resultSet.getString("book_name"));
				bookObj.setBookAuthor(resultSet.getString("book_author"));
				Blob blob = resultSet.getBlob("book_image");
				String base64Image = Utilities.blobToString(blob);
				bookObj.setBookImageString(base64Image);
				bookObj.setBookPublishedDate(new Date(resultSet.getDate("book_published_date").getTime()));
				bookObj.setBookType(BookType.getBookType(resultSet.getInt("book_type")));
			}
			preparedStatement.close();
			connection.close();
			return bookObj;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static ArrayList<BookObj> selectAllBooks() throws Exception {
		try {
			Connection connection = DBConnector.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_BOOKS);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<BookObj> bookList = new ArrayList<BookObj>();

			while (resultSet.next()) {
				BookObj bookObj = new BookObj();
				bookObj.setBookId(resultSet.getInt("book_id"));
				bookObj.setBookName(resultSet.getString("book_name"));
				bookObj.setBookAuthor(resultSet.getString("book_author"));
				Blob blob = resultSet.getBlob("book_image");
				String base64Image = Utilities.blobToString(blob);
				bookObj.setBookImageString(base64Image);
				bookObj.setBookPublishedDate(new Date(resultSet.getDate("book_published_date").getTime()));
				bookObj.setBookType(BookType.getBookType(resultSet.getInt("book_type")));
				bookList.add(bookObj);
			}
			preparedStatement.close();
			connection.close();

			return bookList;
		} catch (Exception exception) {
			throw exception;
		}
	}

	
	public static void updateBook(BookObj bookObj) throws Exception {
		try {
			
			  Connection connection = DBConnector.getConnection(); 
			  PreparedStatement preparedStatement = null;
			  if(bookObj.getBookImage() == null) {//UPDATE tab_book SET book_name=?, book_author=?,book_type=?,book_published_date=? WHERE book_id=?"
				  preparedStatement = connection.prepareStatement(SQL_UPDATE_BOOK_WITH_OUT_IMAGE);				  
				  preparedStatement.setInt(5, bookObj.getBookId());
			  }
			  else {//UPDATE tab_book SET book_name=?, book_author=?,book_image=?,book_type=?,book_published_date=? WHERE book_id=?"
				  preparedStatement = connection.prepareStatement(SQL_UPDATE_BOOK_WITH_IMAGE);				 
				  preparedStatement.setBytes(5, bookObj.getBookImage());
				  preparedStatement.setInt(6, bookObj.getBookId());
			  }
			  preparedStatement.setString(1, bookObj.getBookName());
			  preparedStatement.setString(2, bookObj.getBookAuthor());
			  preparedStatement.setInt(3, bookObj.getBookType().getValue());
			  preparedStatement.setDate(4, new Date(bookObj.getBookPublishedDate().getTime()));
			 
			  
			  preparedStatement.executeUpdate();
			  
			  preparedStatement.close(); connection.close();
			 
		} catch (Exception exception) {
			throw exception;
		}
	}


	public static void deleteBook(int bookId) throws Exception {
		try {
			Connection connection = DBConnector.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BOOK);
			preparedStatement.setInt(1, bookId);

			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception exception) {
			throw exception;
		}
	}
}
