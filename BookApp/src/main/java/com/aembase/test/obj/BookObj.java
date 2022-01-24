package com.aembase.test.obj;

import java.util.Date;

import com.aembase.test.common.BookConstants.BookType;

public class BookObj {
	private Integer bookId;
	private String bookName;
	private String bookAuthor;
	private String bookImageString; // retrival
	private byte[] bookImage; // save
	private BookType bookType;
	private Date bookPublishedDate;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookImageString() {
		return bookImageString;
	}

	public void setBookImageString(String bookImageString) {
		this.bookImageString = bookImageString;
	}

	public byte[] getBookImage() {
		return bookImage;
	}

	public void setBookImage(byte[] bookImage) {
		this.bookImage = bookImage;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	

	public Date getBookPublishedDate() {
		return bookPublishedDate;
	}

	public void setBookPublishedDate(Date bookPublishedDate) {
		this.bookPublishedDate = bookPublishedDate;
	}

	@Override
	public String toString() {
		return "[" + bookId + "," + bookName + "," + bookAuthor + "]";
	}

}
