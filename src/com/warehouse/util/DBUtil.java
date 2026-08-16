package com.warehouse.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBUtil {

public static Connection getDBConnection() throws SQLException{
	Connection con=null;
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String dbURL="jdbc:oracle:thin:@localhost:1521:xe";
	    con= DriverManager.getConnection(dbURL,"system","a");
	    con.setAutoCommit(false);
		}
	catch(ClassNotFoundException e){
		e.printStackTrace();
	}
	return con;
}
}
