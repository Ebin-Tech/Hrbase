package com.aembase.test.common;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;


public class BookConstants {
	public enum BookType {		
		NOVEL(1), 		
		POEM(2), 		
		SHORT_STORY(3),		
		FICTION(4);

		private static Map<Integer, BookType> bookTypeMap = new HashMap<Integer, BookType>();
		private static JSONObject bookObject = new JSONObject();
		static {
			for (BookType userType : BookType.values()) {				
				bookTypeMap.put(userType.value, userType);				
				bookObject.put(userType.value, userType.toString());				
			}
		}
		private int value;
	
		public int getValue() {
			return value;
		}

		public static BookType getBookType(int typeValue) {
			return bookTypeMap.get(typeValue);
		}

		private BookType(int value) {
			this.value = value;
		}
		public static JSONObject getBookJson() {
			return bookObject;
		}
	}
}
