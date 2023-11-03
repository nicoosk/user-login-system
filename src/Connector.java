
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.DriverManager;

public class Connector {
	Connection c;

	public Connection getConnection(String url, String user, String pass) {
		try (Connection connection = DriverManager.getConnection(url, user, pass)) {
			c = connection;
			System.out.println("Connection successful");
		} catch (SQLException e) {
			System.out.println("Error while trying to connect to database: " + e);

		}
		return c;
	}
}
