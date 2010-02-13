package data.objects;

import helper.Helpers;

/**
 * User.java, package: data.objects
 * Object class for users.
 */
public class User {

	private int id;
	private String name;
	private String password;


	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

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
}
