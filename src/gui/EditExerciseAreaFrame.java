package gui;

import data.objects.*;
import data.DBConnection;
import data.ExerciseAreas;

/**
 * EditExerciseAreaFrame.java, package: gui
 */
public class EditExerciseAreaFrame extends Frame {

	private DBConnection connection;
	private String username;

	public EditExerciseAreaFrame(DBConnection connection, String username) {
		initComponents();

		this.connection = connection;
		this.username = username;
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_header = new javax.swing.JLabel();
        cb_learn_table = new javax.swing.JComboBox();
        lbl_learn_table = new javax.swing.JLabel();
        btn_edit = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_header.setFont(new java.awt.Font("Dialog", 1, 18));
        lbl_header.setText("Edit Learn table");

        lbl_learn_table.setText("Learn Table:");

        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_header)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_learn_table)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_learn_table, 0, 136, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_edit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_header)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_learn_table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_learn_table))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_edit)
                    .addComponent(btn_cancel))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
		String areaname = cb_learn_table.getSelectedItem().toString();
		new EditFrame(connection, areaname).setVisible(true);
		this.toggleVisibility();
	}//GEN-LAST:event_btn_editActionPerformed

	private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
		this.toggleVisibility();
	}//GEN-LAST:event_btn_cancelActionPerformed

       /*
	* This method shows all available ExerciseAreas in the JComboBox.
	*/
	private void showExerciseAreasInJComboBox()
	{
		cb_learn_table.removeAllItems();
		ExerciseAreas exerciseareas = ExerciseAreas.getAllExerciseAreasFromUser(
						connection, this.username);
		for (ExerciseArea e : exerciseareas) {
			cb_learn_table.addItem(e);
		}
	}

	public void refreshCbExerciseArea() {		
		this.showExerciseAreasInJComboBox();
	}

	@Override
	public void toggleVisibility() {
		super.toggleVisibility();
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_edit;
    private javax.swing.JComboBox cb_learn_table;
    private javax.swing.JLabel lbl_header;
    private javax.swing.JLabel lbl_learn_table;
    // End of variables declaration//GEN-END:variables
}
