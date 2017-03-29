package org.social.heartbeat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBConnection {

	private static String path             = "com.mysql.jdbc.Driver";
	private static String db_name          = "socialheartbeat";
	private static String url              = "jdbc:mysql://localhost/socialheartbeat";
	private static String user             = "root";
	private static String password         = "";
	private static java.sql.Connection con = null;

	
	
	public Connection getConnection() throws SQLException, ClassNotFoundException
	{

		Class.forName(path);

	    con = DriverManager.getConnection(url, user, password);
		return con;                      
		
	}
	
	
	public static void closeConnection(Connection con) throws SQLException, ClassNotFoundException
	{
		if(con!=null)
		{
			con.close();
		}
	}
	
	
	public static void closePreparedStatment(PreparedStatement ps1) throws SQLException, ClassNotFoundException
	{
		if(ps1!=null)
		{
			ps1.close();
		}
	}

	
}

