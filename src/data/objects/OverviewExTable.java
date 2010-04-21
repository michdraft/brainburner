package data.objects;

import data.DBConnection;
import helper.Helpers;
import helper.ButtonRenderer;
import helper.ButtonEditor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OverviewExTable extends JScrollPane {
	private DBConnection connection;
	private String areaname;
	private int exerciseid;
	private DefaultTableModel tableModel;

	public OverviewExTable(DBConnection connection, String areaname, int exerciseid) {
		this.connection = connection;
		this.areaname = areaname;
		this.exerciseid = exerciseid;

		this.refresh();
		setVisible(true);

	}

       /*
	* Die insert-Methode zeigt alle Fragen und Antworten des gewählten Übungsbereichs an!
	*/
	public Object[][] insert(DBConnection connection, DefaultTableModel model, String areaname) {
		String query =  "select POOL.QUESTION, POOL.ANSWER " +
			        "from EXERCISEAREA join POOL " +
				"on EXERCISEAREA.ID = POOL.EXERCISEAREAID " +
				"where EXERCISEAREA.AREANAME = '" + areaname + "'";

		ResultSet result_set = connection.queryDB(query);
		
		ArrayList<Object[]> list = new ArrayList<Object[]> ();
		try {
			while(result_set.next()) {
				Object[] obj = new Object[4];
				obj[0] = result_set.getString("QUESTION");
				obj[1] = result_set.getString("ANSWER");
				obj[2] = "Edit";
				obj[3] = "Delete";
				list.add(obj);
			}
		} catch (SQLException e) {
			Helpers.debug("insert: Error: %s\n", e.getMessage());
		}

		Object[][] object = new Object[list.size()][4];
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<4; j++) {
				object[i][j] = list.get(i)[j];
			}
		}
		return object;
	}

	public void refresh() {
		JTable tabelle = new JTable();
		tableModel = new DefaultTableModel();
		tableModel.setDataVector(this.insert(connection, tableModel, areaname),
					 new Object[]{"Question", "Answer", "Edit", "Delete"});
		tabelle.setModel(tableModel);
		tabelle.getColumn("Question").setMinWidth(300);
		tabelle.getColumn("Answer").setMinWidth(300);
		tabelle.getColumn("Edit").setCellRenderer(new ButtonRenderer());
		tabelle.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), connection, tabelle, this, this.exerciseid));
		tabelle.getColumn("Delete").setCellRenderer(new ButtonRenderer());
		tabelle.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), connection, tabelle, this, this.exerciseid));
		this.setViewportView(tabelle);
	}
}
