package com.aembase.test.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.aembase.test.dao.BookDAO;
import com.aembase.test.obj.BookObj;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/bookdeleter")
public class BookDeleter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookDeleter() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {
			int bookId = Integer.parseInt(request.getParameter("bookId"));		
			System.out.println(bookId);
			BookDAO.deleteBook(bookId);	
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
