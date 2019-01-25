package com.educorp.eduinteractive.dao.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager	 {


	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://10.53.124.208:3306/educorp?"
			+ "useUnicode=true&useJDBCCompliantTimezoneShift=true"
			+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";

	//  Database credentials
	static final String USER = "eclipse";
	static final String PASS = "eclipse";

	static {

		try {

			 Class.forName(JDBC_DRIVER);
			
		} catch (Exception e) {
			e.printStackTrace();
 
		}

	}

	private ConnectionManager() {}

	public final static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}
	
}