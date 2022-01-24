package com.aembase.test.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.aembase.test.dao.BookDAO;
import com.aembase.test.obj.BookObj;

@WebServlet("/bookviewer")
public class BookViewer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public BookViewer() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			BookObj bookObj = BookDAO.selectBook(bookId);
			
			JSONObject bookObject = new JSONObject();
			bookObject.put("bookId", bookObj.getBookId());
			bookObject.put("bookName", bookObj.getBookName());
			bookObject.put("bookAuthor", bookObj.getBookAuthor());
			bookObject.put("bookType", bookObj.getBookType().getValue());
			bookObject.put("bookImageString", bookObj.getBookImageString());
			bookObject.put("bookPublishedDate", new SimpleDateFormat("yyyy-MM-dd").format(bookObj.getBookPublishedDate()));
			
			PrintWriter out = response.getWriter();
			out.println(bookObject);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
