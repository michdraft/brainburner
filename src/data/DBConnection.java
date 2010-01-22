package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import helper.Helpers;

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
			System.out.printf("Error: %s\n", e.getMessage());
		}
	}

	public void updateDB(String query) {
		try {
			int n = statement.executeUpdate(query);
			Helpers.debug("updateDB: %d lines updated.\n");
		} catch (Exception e) {
			Helpers.debug("Error: " + e.getMessage());
		}
	}

	public ResultSet queryDB(String query) {
		/* FIXME: Needs to be implemented :) */
		return null;
	}
}
