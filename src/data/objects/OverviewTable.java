package data.objects;

import data.DBConnection;
import data.Users;
import helper.Helpers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OverviewTable extends JScrollPane {

	private DBConnection connection;
	private String username;
	private JTable tabelle = new JTable();
	private DefaultTableModel tableModel;	

	public OverviewTable(DBConnection connection, String user)
	{
		this.connection = connection;
		this.username = user;
		this.setViewportView(tabelle);
		tableModel = new DefaultTableModel(new String[]{"Name", "Sprache", "Anzahl Datensätze"}, 0);
		tabelle.setModel(tableModel);
		this.insert(connection, tableModel);
	}

	public void refresh() {
		tableModel = new DefaultTableModel(new String[]{"Name", "Sprache", "Anzahl Datensätze"}, 0);
		tabelle.setModel(tableModel);
		this.insert(connection, tableModel);
	}

	/*
	 * Die insert-Methode zeigt alle Übungsbereiche des eingeloggten Nutzers an!
	 */
	public void insert(DBConnection connection, DefaultTableModel model) {

		ArrayList<String[]> arrayList = new ArrayList<String[]> ();
		int userID = Users.getUser(connection, this.username).getId();

		arrayList = getAreanameAndId(arrayList, userID);

		for(int i=0; i<arrayList.size(); i++) {
			Vector modelRow = new Vector(model.getColumnCount());
			modelRow.addElement(arrayList.get(i)[0]);
			modelRow.addElement(arrayList.get(i)[2]);
			modelRow.addElement(arrayList.get(i)[3]);
			model.addRow(modelRow);
		}
	}
	
	public ArrayList<String[]> getAreanameAndId(ArrayList<String[]> arrayList, int userid) {

		String query = "select EXERCISEAREA.AREANAME, EXERCISEAREA.ID " +
			       "from USER_TABLE_REL join EXERCISEAREA " +
			       "on USER_TABLE_REL.EXERCISEAREAID = EXERCISEAREA.ID " +
			       "where USER_TABLE_REL.USERID = " + userid;

		ResultSet result_set = connection.queryDB(query);

		try {
			while (result_set.next()) {
				String[] array = new String[4];
				array[0] = result_set.getString("AREANAME");
				array[1] = result_set.getString("ID");
				arrayList.add(array);
			}
		} catch (SQLException e) {
			Helpers.debug("insert: Error: %s\n", e.getMessage());
		}

		return getLanguagename(arrayList);
	}

	public ArrayList<String[]> getLanguagename(ArrayList<String[]> arrayList) {
		for(int i=0; i<arrayList.size(); i++) {
			String query_languagename =
				"select LANGUAGES.LANGUAGENAME " +
				"from LANG_TABLE_REL join LANGUAGES " +
				"on LANG_TABLE_REL.LANGUAGEID = LANGUAGES.ID " +
				"where LANG_TABLE_REL.EXERCISEAREAID = " + arrayList.get(i)[1];
			ResultSet result_set = connection.queryDB(query_languagename);
			try {
				while(result_set.next()) {
					arrayList.get(i)[2] = result_set.getString("LANGUAGENAME");
				}
			} catch (SQLException e) {
				Helpers.debug("insert: Error: %s\n", e.getMessage());
			}
		}
		return getNumber(arrayList);
	}

	public ArrayList<String[]> getNumber(ArrayList<String[]> arrayList) {
		for(int i=0; i<arrayList.size(); i++) {
			String query_number =
				"select count(*) as number " +
				"from POOL " +
				"where POOL.EXERCISEAREAID = " + arrayList.get(i)[1];

			ResultSet result_set = connection.queryDB(query_number);
			try {
				while(result_set.next()) {
					arrayList.get(i)[3] = result_set.getString("number");
				}
			} catch(SQLException e) {
				Helpers.debug("insert: Error: %s\n", e.getMessage());
			}
		}
		return arrayList;
	}
}
