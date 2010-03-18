package helper;

import data.DBConnection;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ButtonEditor extends DefaultCellEditor {
	private DBConnection connection;
	protected JButton button;
	private String label;
	private boolean isPushed;

	public ButtonEditor(JCheckBox checkBox, DBConnection connection) {
		super(checkBox);
		this.connection = connection;
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
		if (isPushed)  {
			JOptionPane.showMessageDialog(button ,label + ": noch keine Funktion!");
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

	// in Arbeit
	private boolean deleteDataset(String question, String answer) {
		String sql_delete = "delete from pool " + 
			"where question = '" + question + "' and answer = '" + answer + "'";
		if(connection.updateDB(sql_delete) == 1) {
			return true;
		} else {
			return false;
		}
	}

	// in Arbeit
	private void editDataset(String question, String answer) {

	}
}
