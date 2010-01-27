/*
 * User.java, package: data.objects
 * Object class for users.
 */

package data.objects;

import data.DBConnection;
import helper.Helpers;

public class User {

	private int id;
	private String name;
	private String password;

	public User(String name, String password) {
		this.name = name;
		setPassword(password);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = Helpers.stringToMD5(password);
	}

	public boolean update(DBConnection connection) {
		String query_insert_user = 
			"insert into users(username,password) values('"
			    + this.name + "', '"
			    + this.password + "')";

		if (connection.updateDB(query_insert_user) == 1)
			return true;
		else
			return false;
	}
}
