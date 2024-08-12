package com.rjdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
	private JdbcUtil() {}
	
	static { 
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
		} catch
	    (ClassNotFoundException e) { 
		  e.printStackTrace(); 
		} 
	}
	 
	
	public static Connection getJdbcConnection() throws SQLException, IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\RAJIB\\eclipse-workspace\\JDBC\\application.properties");
		Properties properties=new Properties();
		properties.load(fis);
	
		String url=properties.getProperty("url"); 
		String userName=properties.getProperty("userName");
		String password=properties.getProperty("password");
		Connection connection=DriverManager.getConnection(url, userName, password);
		System.out.println("Connection established with Database...");
		return connection;
	}
	
	public static void cleanUp(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
		if(connection!=null)
			connection.close();
		
		if(statement!=null)
			statement.close();
		
		if(resultSet!=null)
			resultSet.close();
	}
}
