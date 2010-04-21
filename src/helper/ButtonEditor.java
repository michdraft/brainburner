package helper;

import data.DBConnection;
import data.objects.OverviewExTable;
import gui.EditDatasetAreaFrame;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ButtonEditor extends DefaultCellEditor {
	private DBConnection connection;
	protected JButton button;
	private String label;
	private boolean isPushed;
	private int exerciseid;
	private JTable jtable;
	private OverviewExTable parent;

	public ButtonEditor(JCheckBox checkBox, DBConnection connection,
			JTable jtable, OverviewExTable parent, int exerciseid) {
		super(checkBox);
		this.connection = connection;
		this.parent = parent;
		this.jtable = jtable;
		this.exerciseid = exerciseid;
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			   boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value ==null) ? "" : value.toString();
		button.setText( label );
		isPushed = true;
		return button;
	  }

	@Override
	public Object getCellEditorValue() {
		int row = this.jtable.getSelectedRow();
		String question = this.jtable.getValueAt(row, 0).toString();
		String answer = this.jtable.getValueAt(row, 1).toString();

		if (isPushed && button.getText().equals("Delete"))  {
			deleteDataset(question, answer);
		} else if(isPushed && button.getText().equals("Edit")) {
			editDataset(question, answer);
		}
		isPushed = false;
		return new String( label ) ;
	}

	@Override
	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	@Override
	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}

	private boolean deleteDataset(String question, String answer) {

		String sql_delete = "delete from pool " +
			"where question = '" + question + "' and answer = '" + answer + "'" +
			"and exerciseareaid = " + this.exerciseid;
		if(connection.updateDB(sql_delete) == 1) {
			this.parent.refresh();
			return true;
		} else {
			return false;
		}
	}

	private void editDataset(String question, String answer) {
		new EditDatasetAreaFrame(connection, question, answer, this.parent, this.exerciseid).toggleVisibility();
	}
}
