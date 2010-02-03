package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import helper.*;

/**
 * DBConnection.java, Package: data
 * Low-level implementation for the database connection.
 */
public class DBConnection {

	Connection database;
	Statement statement;

	public DBConnection(String db_name, String db_user, String db_pw) {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();

			String connection_string = "jdbc:derby://localhost:1527/" + db_name;
			database = DriverManager.getConnection(connection_string, db_user, db_pw);

			statement = database.createStatement();
		} catch (Exception e) {
			Helpers.debug("Error: %s\n", e.getMessage());
			Messages.showError("Could not connect to database!");
			System.exit(1);
		}
	}

	/**
	 * Executes an update query to the connected database.
	 * @param query The query string.
	 * @return n Number of lines updated. In case of an exception -1.
	 */
	public int updateDB(String query) {
		try {
			int n = statement.executeUpdate(query);
			Helpers.debug("updateDB: Info: %d lines updated.\n", n);
			return n;
		} catch (Exception e) {
			Helpers.debug("updateDB: Error: %s", e.getMessage());
			return -1;
		}
	}

	/**
	 * Querys the connected database and returns a result set.
	 * @param query The query string.
	 * @return resultset The restultset with the containing data. In case
	 * of an error it returns null
	 */
	public ResultSet queryDB(String query) {
		ResultSet resultset;
		try {
			resultset = statement.executeQuery(query);
			return resultset;
		} catch (Exception e) {
			Helpers.debug("queryDB: Error: %s", e.getMessage());
			return null;
		}
	}
}
