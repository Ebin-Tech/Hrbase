package com.aembase.test.control;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.aembase.test.common.BookConstants.BookType;
import com.aembase.test.common.Utilities;
import com.aembase.test.dao.BookDAO;
import com.aembase.test.obj.BookObj;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
@WebServlet("/bookwriter")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class BookWriter extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BookWriter() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String bookName = request.getParameter("txtBookName");
			String bookAuthor = request.getParameter("txtBookAuthor");
			final Part bookImage = request.getPart("bookFile");
			int bookType = Integer.parseInt(request.getParameter("bookType"));
			String bookPublishedDate = request.getParameter("txtBookPublishedDate");
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("bookName", bookName); 
			jsonObject.put("bookAuthor", bookAuthor);
			jsonObject.put("bookPublishedDate", bookPublishedDate);
			
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);  
			String jsonBook = jsonObject.toJSONString();
			BookObj bookObj = mapper.readValue(jsonBook, BookObj.class);			
					
			if (bookImage.getContentType() != null) {
				final byte[] imageBytes = Utilities.partToByteArray(bookImage);
				bookObj.setBookImage(imageBytes);
			}	
			bookObj.setBookType(BookType.getBookType(bookType));

			BookDAO.insertBook(bookObj);		
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		
	}

}
