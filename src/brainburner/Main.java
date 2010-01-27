/*
 * Main.java, package: brainburner
 * Main class of the project. Won't do any magic.
 */

package brainburner;

import data.DBConnection;
import gui.*;

public class Main {
	public static void main(String[] args) {

		String db_name = "brainburner_test";
		String db_user = "test";
		String db_password = "test";

		DBConnection connection = new DBConnection(db_name, db_user, db_password);

		new UsersFrame(connection); /* just for testing */
		//new TablesFrame(connection); /* just for testing */
	}
}
