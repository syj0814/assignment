package bc;

import java.io.*;
import java.sql.*;
import java.util.*;

public class connectToDB {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/shinyj?useSSL=false&serverTimezone=Asia/Seoul";
	static final String USERNAME = "root";
	static final String PASSWORD = "!@asdfQWER";
	
	Statement stmt = null;
	
	public Connection getConn(Connection conn) throws ClassNotFoundException, SQLException {
		
		//JDBC 드라이버 연결
		Class.forName(JDBC_DRIVER);
			
		//DB 연결
		conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);	

		return conn;
	}
	
	public void closeConn(Connection conn) throws SQLException {
		conn.close();
	}
	
	public Statement getStmt(Connection conn, Statement stmt) throws ClassNotFoundException, SQLException {
			
		stmt = conn.createStatement();
	
		return stmt;
	}
	
	public void closeStmt(Statement stmt) throws SQLException {
		stmt.close();
	}
}
