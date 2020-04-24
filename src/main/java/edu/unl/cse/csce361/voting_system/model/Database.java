package edu.unl.cse.csce361.voting_system.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public static final String url = "jdbc:mysql://cse.unl.edu/jerickson?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String username = "jerickson";
	public static final String password = "0HRh8TAB";
	
	// Connects our program to the SQL Invoice database
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
