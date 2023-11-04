package db_manage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Connector {
	Connection c;
	String url, user, pass;

	public Connector(){
	}

	public Connector(String url){
		this.url = url;
	}
	public Connector(String user, String pass){
		this.user = user;
		this.pass = pass;
	}
	public Connector(String url, String user, String pass){
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	public Connection getConnection() throws SQLException {
		try{
			c = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection successful");
		} catch (SQLException e) {
			System.out.println("Error while trying to connect to database: " + e);
			c.close();
			System.out.println("Connection " +c.getMetaData().getConnection() + "closed.");
		}
		return c;
	}
	public Connection getConnection(String url) throws SQLException {
		try{
			c = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection successful");
		} catch (SQLException e) {
			System.out.println("Error while trying to connect to database: " + e);
			c.close();
			System.out.println("Connection " +c.getMetaData().getConnection() + "closed.");
		}
		return c;
	}
	public Connection getConnection(String user, String pass) throws SQLException {
		try {
			c = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection successful");
		} catch (SQLException e) {
			System.out.println("Error while trying to connect to database: " + e);
			c.close();
		}
		return c;
	}
	public Connection getConnection(String url, String user, String pass) throws SQLException {
		try {
			c = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection successful");
		} catch (SQLException e) {
			System.out.println("Error while trying to connect to database: " + e);
			c.close();
		}
		return c;
	}


}
