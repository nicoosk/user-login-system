package data_manager;

import data_manager.handlePassCipher.Encrypt;
import org.jetbrains.annotations.NotNull;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Login {
	String user, pass;
	Connection c;
	public Login(){
	}
	public Login(Connection c){
		this.c = c;
	}
	public Login(String user, String pass){
		this.user = user;
		this.pass = pass;
	}
	public Login(Connection c, String user, String pass){
		this.c = c;
		this.user = user;
		this.pass = pass;
	}
	public boolean logWithUser() {
		boolean isUserInDB = false;
		String sql = "SELECT username, pass FROM users;";
		try (PreparedStatement pt = c.prepareStatement(sql)) {
			ResultSet rs = pt.executeQuery();
			isUserInDB = userLoggedIn(rs, user, pass);
		} catch (SQLException e) {
			System.out.println("Error while executing query: " + e.getMessage());
		}
		return isUserInDB;
	}
	public boolean logWithUser(@NotNull Connection c) {
		boolean isUserInDB = false;
		String sql = "SELECT username, pass FROM users;";
		try (PreparedStatement pt = c.prepareStatement(sql)) {
			ResultSet rs = pt.executeQuery();
			isUserInDB = userLoggedIn(rs, user, pass);
		} catch (SQLException e) {
			System.out.println("Error while executing query: " + e.getMessage());
		}
		return isUserInDB;
	}
	public boolean logWithUser(@NotNull String user, @NotNull String pass) {
		boolean isUserInDB = false;
		String sql = "SELECT username, pass FROM users;";
		try (PreparedStatement pt = c.prepareStatement(sql)) {
			ResultSet rs = pt.executeQuery();
			isUserInDB = userLoggedIn(rs, user, pass);
		} catch (SQLException e) {
			System.out.println("Error while executing query: " + e.getMessage());
		}
		return isUserInDB;
	}
	public boolean logWithUser(@NotNull Connection c, @NotNull String user, @NotNull String pass) {
		boolean isUserInDB = false;
		String sql = "SELECT username, pass FROM users;";
		try (PreparedStatement pt = c.prepareStatement(sql)) {
			ResultSet rs = pt.executeQuery();
			isUserInDB = userLoggedIn(rs, user, pass);
		} catch (SQLException e) {
			System.out.println("Error while executing query: " + e.getMessage());
		}
		return isUserInDB;
	}

	private boolean userLoggedIn(@NotNull ResultSet rs, String user, String pass) {
		try {
			while (rs.next()) {
				String userToCompare = rs.getString("username");
				String passToCompare = rs.getString("pass");
				passToCompare = new Encrypt().decryptString(passToCompare);
				if (userToCompare.equals(user) && passToCompare.equals(pass)) return true;
			}
		} catch (SQLException e) {
			System.out.println("Error with user recovering: " + e);
		}
		return false;
	}

}
