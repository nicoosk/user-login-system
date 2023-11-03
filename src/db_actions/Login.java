package con;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Login {

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
		ArrayList<String> userList = new ArrayList<>();
		try {
			while (rs.next()) {
				String userToCompare = rs.getString("username");
				String passToCompare = rs.getString("pass");
				if (userToCompare.equals(user) && passToCompare.equals(pass)) return true;
			}
		} catch (SQLException e) {
			System.out.println("Error with user recovering: " + e);

		}
		return false;
	}

}
