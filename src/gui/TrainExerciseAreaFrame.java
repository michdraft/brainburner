package gui;

import data.objects.*;
import data.DBConnection;
import data.ExerciseAreas;
import helper.Helpers;
import helper.Messages;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * TrainExerciseAreaFrame.java, package: gui
 */
public class TrainExerciseAreaFrame extends Frame {

	private DBConnection connection;
	private String username;
	private ArrayList<String[]> datasets;

	public TrainExerciseAreaFrame(DBConnection connection, String username) {
		initComponents();

		this.connection = connection;
		this.username = username;
		this.datasets = new ArrayList<String[]> ();
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_header = new javax.swing.JLabel();
        cb_learn_table = new javax.swing.JComboBox();
        lbl_learn_table = new javax.swing.JLabel();
        btn_train = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();

	  setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);						

        lbl_header.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbl_header.setText("Train Learn table");

        lbl_learn_table.setText("Learn Table:");

        btn_train.setText("train");
        btn_train.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_trainActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_learn_table, 0, 150, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_train)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cancel)))))
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
                    .addComponent(btn_train)
                    .addComponent(btn_cancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void btn_trainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_trainActionPerformed
		this.toggleVisibility(); this.createArrayList();
		System.out.println(this.datasets.size());
		if(checkIfArrayIsEmpty() == false) {
			new LearnFrame(connection, this.datasets, new User(null, null, 1), new ExerciseArea(null, 1)).toggleVisibility();
		} else {
			Messages.showInfo(cb_learn_table.getSelectedItem().toString()+
						" is empty!");
			this.toggleVisibility();
		}
	}//GEN-LAST:event_btn_trainActionPerformed

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

	public boolean checkComboBox() {
		if(cb_learn_table.getItemCount() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void toggleVisibility() {
		super.toggleVisibility();
		this.datasets = new ArrayList<String[]> ();
	}

	public void createArrayList() {
		String query_datasets = "select question, answer " +
					"from POOL join EXERCISEAREA " +
					"on EXERCISEAREA.AREANAME = '" + cb_learn_table.getSelectedItem().toString() + "'" +
					"where EXERCISEAREA.ID = POOL.EXERCISEAREAID";
		ResultSet result_set = connection.queryDB(query_datasets);
		int count = 0;
		try {
			while (result_set.next()) {
				String[] array = new String[2];
				array[0] = result_set.getString("question");
				array[1] = result_set.getString("answer");
				this.datasets.add(count, array);
				count++;
			}
		} catch (SQLException e) {
			Helpers.debug("select: Error: %s\n", e.getMessage());
		}
	}

	public boolean checkIfArrayIsEmpty() {
		if(this.datasets.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_train;
    private javax.swing.JComboBox cb_learn_table;
    private javax.swing.JLabel lbl_header;
    private javax.swing.JLabel lbl_learn_table;
    // End of variables declaration//GEN-END:variables
}
