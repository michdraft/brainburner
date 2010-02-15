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
public class OverviewTable extends JScrollPane {

	private JTable tabelle = new JTable();

	public OverviewTable()
	{
		this.setViewportView(tabelle);
		DefaultTableModel tableModel =
			new DefaultTableModel(new String[]{"Name", "Sprache", "Anzahl Datensätze"}, 0);
		tableModel.setRowCount(10);
		tabelle.setModel(tableModel);
		this.showOverviewTable(tableModel);
	}

	/*
	 * This method shows the OverviewTable of the logged user
	 */

	public void showOverviewTable(DefaultTableModel model)
	{
		int columnCount = model.getColumnCount();
		Vector newDatas = new Vector(columnCount);
		model.addRow(newDatas);
	}

	/*
	 * Die insert-Methode zeigt alle Übungsbereiche des eingeloggten Nutzers an!
	 */
	
	/*
	public boolean insert(DBConnection connection) {
		if (connection.updateDB(query_create_table) != -1 &&
		    connection.updateDB(query_insert_table) == 1)
			return true;
		else
			return false;
	}*/

	public boolean update(DBConnection connection) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean drop(DBConnection connection) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
