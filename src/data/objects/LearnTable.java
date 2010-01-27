/*
 * LearnTable,java, package: data.objects
 * Data objects for the learn tables (as created in TablesFrame)
 */

package data.objects;

import data.DBConnection;

public class LearnTable {

	private int id;
	private int language;
	private String name;

	public LearnTable(int language, String name) {
		this.language = language;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the language
	 */
	public int getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(int language) {
		this.language = language;
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

	public boolean createLearnTable(DBConnection connection) {
		String query_insert_table =
			"insert into lang_table_rel(language, name) values (" +
			this.language + ",'" + this.name +"')";

		String query_create_table =
			"create table " + this.name + " (" +
				"id int primary key not null generated always as identity," +
				"question varchar(100)," +
				"answer varchar(100)" + 
			")";

		if (connection.updateDB(query_create_table) != -1 &&
		    connection.updateDB(query_insert_table) == 1)
			return true;
		else
			return false;
	}
}
