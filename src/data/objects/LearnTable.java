package data.objects;

import data.DBConnection;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * LearnTable,java, package: data.objects
 * Data objects for the learn tables (as created in TablesFrame)
 */
public class LearnTable extends JScrollPane implements DataObject {

	private int id;
	private int language;
	private String name;
	private JTable tabelle = new JTable();

	public LearnTable(int language, String name) {
		this.language = language;
		this.name = name;
		
	}

	public LearnTable()
	{
		this.setViewportView(tabelle);
		DefaultTableModel tableModel =
			new DefaultTableModel(new String[]{"Name", "Sprache", "Anzahl Datensätze"}, 0);
		tableModel.setRowCount(10);
		tabelle.setModel(tableModel);
		this.addLearnTable("Keywords", "Englisch", tableModel);
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

	/*
	 * This method add a new LearnTable row inside the Jtable DefaultTableModel
	 * under construction!
	 */
	public void addLearnTable(String name, String Sprache, DefaultTableModel model)
	{
		int columnCount = model.getColumnCount();
		Vector newDatas = new Vector(columnCount);
		model.addRow(newDatas);
	}

	public boolean insert(DBConnection connection) {
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

	public boolean update(DBConnection connection) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean drop(DBConnection connection) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
