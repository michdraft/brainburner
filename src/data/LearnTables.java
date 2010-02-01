package data;

import data.objects.LearnTable;

public class LearnTables {
	public static boolean addLearnTable(DBConnection connection, LearnTable learn_table) {
		String query_insert_table = String.format(
			"insert into lang_table_rel(language, name) values (%d, '%s')",
			learn_table.getLanguage(), learn_table.getName());

		String query_create_table = String.format(
			"create table %s (" +
				"id int primary key not null generated always as identity," +
				"question varchar(100)," +
				"answer varchar(100)" +
			")",
			learn_table.getName());

		if (connection.updateDB(query_create_table) != -1 &&
		    connection.updateDB(query_insert_table) == 1)
			return true;
		else
			return false;
	}
}
