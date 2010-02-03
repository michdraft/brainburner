package brainburner;

import data.DBConnection;
import gui.*;

/**
 * Main.java, package: brainburner
 * Main class of the project. Won't do any magic.
 */
public class Main {
	public static void main(String[] args) {

		String db_name = "brainburner_test";
		String db_user = "test";
		String db_password = "test";

		DBConnection connection = new DBConnection(db_name, db_user, db_password);
		new LoginFrame(connection);
		//new LanguageFrame(connection);
	}
}
