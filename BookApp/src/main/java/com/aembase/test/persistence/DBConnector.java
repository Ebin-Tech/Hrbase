package com.aembase.test.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/db_lib";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";
	
	public static Connection getConnection() throws Exception{
		try {
			Class.forName(DRIVER_NAME);
			Connection connection = DriverManager.getConnection(CONNECTION_URL,USER_NAME,PASSWORD);
			return connection;
		
		}catch (Exception exception) {
			throw exception;
		}			
	}
}
