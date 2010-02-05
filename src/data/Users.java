package data;

import helper.Helpers;
import data.objects.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Users.java, package: data
 * This class extends ArrayList and contains User-Objects.
 * It's used to load the users from the database and bring them in a
 * suitable form and also offers a method to get a single user by Name(getUser).
 */
public class Users extends ArrayList<User> {

	DBConnection connection;

	public Users(DBConnection connection) {
		this.connection = connection;
		getAllUsers();
	}

	private void getAllUsers() {
		String query = "select * from users";

		ResultSet result_set = connection.queryDB(query);

		try {
			while (result_set.next()) {
				this.add(
					new User(result_set.getString("USERNAME"),
						 result_set.getString("PASSWORD"),
						 result_set.getInt("ID")));
			}
		} catch (Exception e) {
			Helpers.debug("getAllUsers: Error: %s\n", e.getMessage());
		}
	}

	public static User getUser(DBConnection connection, String user) {
		String query = String.format("select * from users where username='%s'", user);

		ResultSet result_set = connection.queryDB(query);

		try {
			if (result_set.next()) {
				return new User(result_set.getString("USERNAME"),
						result_set.getString("PASSWORD"),
						result_set.getInt("ID"));
			} else {
				return null;
			}
		} catch (Exception e) {
			Helpers.debug("getUser: Error: %s\n", e.getMessage());
			return null;
		}
	}

	public static User getUsername(DBConnection connection, String user)
	{
		String query = String.format("select username from users where username'%s'", user);
		ResultSet result_set = connection.queryDB(query);
		return (User) result_set;
	}
	
}
