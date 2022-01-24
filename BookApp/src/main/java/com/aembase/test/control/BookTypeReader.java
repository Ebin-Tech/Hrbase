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

import com.aembase.test.common.BookConstants.BookType;
import com.aembase.test.dao.BookDAO;
import com.aembase.test.obj.BookObj;

@WebServlet("/BookTypeReader")
public class BookTypeReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			PrintWriter out = response.getWriter();
			out.println(BookType.getBookJson());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	}

}
