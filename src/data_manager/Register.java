package data_manager;

import org.jetbrains.annotations.NotNull;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {
	Connection c;
	String user, pass;

	public Register(){
	}
	public Register(Connection c){
		this.c = c;
	}
	public Register(String user, String pass){
		this.user = user;
		this.pass = pass;
	}
	public Register(Connection c, String user, String pass){
		this.c = c;
		this.user = user;
		this.pass = pass;
	}
	public int registerUser(){
		int rowsAffected = 0;
		String sql = "INSERT INTO users (username, pass) VALUE (?,?);";
		try (PreparedStatement pt = c.prepareStatement(sql)){
			insertIntoQuery(pt, user, pass);
			rowsAffected = pt.executeUpdate();
		} catch (SQLException e){
			System.out.println("Error while creating user: " + e);
		}
		return rowsAffected;
	}
	public int registerUser(@NotNull Connection c){
		int rowsAffected = 0;
		String sql = "INSERT INTO users (username, pass) VALUE (?,?);";
		try (PreparedStatement pt = c.prepareStatement(sql)){
			insertIntoQuery(pt, user, pass);
			rowsAffected = pt.executeUpdate();
		} catch (SQLException e){
			System.out.println("Error while creating user: " + e);
		}
		return rowsAffected;
	}
	public int registerUser(@NotNull String user, @NotNull String pass){
		int rowsAffected = 0;
		String sql = "INSERT INTO users (username, pass) VALUE (?,?);";
		try (PreparedStatement pt = c.prepareStatement(sql)){
			insertIntoQuery(pt, user, pass);
			rowsAffected = pt.executeUpdate();
		} catch (SQLException e){
			System.out.println("Error while creating user: " + e);
		}
		return rowsAffected;
	}
	public int registerUser(@NotNull Connection c, @NotNull String user, @NotNull String pass){
		int rowsAffected = 0;
		String sql = "INSERT INTO users (username, pass) VALUE (?,?);";
		try (PreparedStatement pt = c.prepareStatement(sql)){
			insertIntoQuery(pt, user, pass);
			rowsAffected = pt.executeUpdate();
		} catch (SQLException e){
			System.out.println("Error while creating user: " + e);
		}
		return rowsAffected;
	}

	private void insertIntoQuery(@NotNull PreparedStatement pt, String user, String pass){
		try {
			pt.setString(1, user);
			pt.setString(2, pass);
		} catch(SQLException e){
			System.out.println("Error while creating SQL query: " + e);
		}
	}


}
