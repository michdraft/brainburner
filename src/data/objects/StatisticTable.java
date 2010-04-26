package data.objects;

import data.DBConnection;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StatisticTable extends JScrollPane {
	private DBConnection connection;
	private DefaultTableModel tableModel;
	private ArrayList<Object[]> list;

	public StatisticTable(DBConnection connection, ArrayList<Object[]> list) {
		this.connection = connection;
		this.list = list;
		this.refresh();
		this.setVisible(true);
	}

	public Object[][] insert(DBConnection connection) {
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
		tableModel.setDataVector(this.insert(connection),
					 new Object[]{"User", "Area", "%", "Datum"});
		tabelle.setModel(tableModel);
		this.setViewportView(tabelle);
	}
}
