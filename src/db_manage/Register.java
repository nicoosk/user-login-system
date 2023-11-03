package db_manage;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {

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
