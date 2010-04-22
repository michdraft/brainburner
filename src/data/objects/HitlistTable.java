package data.objects;

import data.DBConnection;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HitlistTable extends JScrollPane {
	private DBConnection connection;
	private DefaultTableModel tableModel;
	private ArrayList<Object[]> list;

	public HitlistTable(DBConnection connection, ArrayList<Object[]> list) {
		this.connection = connection;
		this.list = list;
		this.refresh();
		this.setVisible(true);
	}

	public Object[][] insert(DBConnection connection, DefaultTableModel model) {
		Object[][] object = new Object[list.size()][2];
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<2; j++) {
				object[i][j] = list.get(i)[j];
			}
		}
		return object;
	}

	public void refresh() {
		JTable tabelle = new JTable();
		tableModel = new DefaultTableModel();
		tableModel.setDataVector(this.insert(connection, tableModel),
					 new Object[]{"Question", "Answer"});
		tabelle.setModel(tableModel);
		tabelle.getColumn("Question").setMinWidth(300);
		tabelle.getColumn("Answer").setMinWidth(300);
		this.setViewportView(tabelle);
	}
}
