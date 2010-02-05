package data.objects;

import data.DBConnection;
import helper.Helpers;

/**
 * User.java, package: data.objects
 * Object class for users.
 */
public class User implements DataObject {

	private int id;
	private String name;
	private String password;
	private DBConnection connection;

	public User(String name, String password, int id) {
		this.name = name;
		this.password = password;
		this.id = id;
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}	

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public void encryptPassword() {
		this.password = Helpers.stringToMD5(this.password);
	}

	public boolean insert(DBConnection connection) {
		String query_insert_user = 
			"insert into users(username,password) values('"
			    + this.name + "', '"
			    + this.password + "')";

		if (connection.updateDB(query_insert_user) == 1)
			return true;
		else
			return false;
	}

	public boolean update(DBConnection connection) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean drop(DBConnection connection) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
