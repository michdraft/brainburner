package data;

import helper.Helpers;
import data.objects.User;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Users.java, package: data
 * This class extends ArrayList and contains User-Objects.
 * It's used to load the users from the database and bring them in a
 * suitable form and also offers a method to get a single user by Name(getUser).
 */
public class Users extends ArrayList<User> {
	public static boolean addUser(DBConnection connection, User user) {
		String query = String.format("insert into users(username,password) values('%s', '%s')",
			user.getName(), user.getPassword());

		if (connection.updateDB(query) == 1)
			return true;
		else
			return false;
	}

	public static Users getAllUsers(DBConnection connection) {
		String query = "select * from users";

		ResultSet result_set = connection.queryDB(query);
		Users users = new Users();

		try {
			while (result_set.next()) {
				users.add(
					new User(result_set.getString("USERNAME"),
						 result_set.getString("PASSWORD"),
						 result_set.getInt("ID")));
			}

			return users;
		} catch (Exception e) {
			Helpers.debug("getAllUsers: Error: %s\n", e.getMessage());
			return null;
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
}
