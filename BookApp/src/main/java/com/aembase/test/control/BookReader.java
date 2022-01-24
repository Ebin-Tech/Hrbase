package com.aembase.test.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.aembase.test.dao.BookDAO;
import com.aembase.test.obj.BookObj;
@WebServlet("/bookreader")
public class BookReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public BookReader() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ArrayList<BookObj> bookList =  BookDAO.selectAllBooks();
			JSONArray bookArray = new JSONArray();
			for(BookObj bookObj : bookList) {
				JSONObject bookObject = new JSONObject();
				bookObject.put("bookId", bookObj.getBookId());
				bookObject.put("bookName", bookObj.getBookName());
				bookObject.put("bookAuthor", bookObj.getBookAuthor());
				bookObject.put("bookImageString", bookObj.getBookImageString());
				bookArray.add(bookObject);
			}
			PrintWriter out = response.getWriter();
			out.println(bookArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
