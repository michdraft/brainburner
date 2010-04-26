package gui;

import data.DBConnection;
import data.objects.OverviewExTable;

public class EditDatasetAreaFrame extends Frame {

	private DBConnection connection;
	private String question;
	private String answer;
	private int exerciseid;
	private OverviewExTable parent;

	public EditDatasetAreaFrame(DBConnection connection, String question, String answer,
		OverviewExTable parent, int exerciseid) {
		initComponents();

		this.setAlwaysOnTop(true);
		this.connection = connection;
		this.parent = parent;
		this.exerciseid = exerciseid;
		
		this.question = question;
		this.answer = answer;

		txt_question.setText(question);
		txt_answer.setText(answer);

		this.getRootPane().setDefaultButton(btn_edit);
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_header = new javax.swing.JLabel();
        lbl_question = new javax.swing.JLabel();
        btn_edit = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        txt_question = new javax.swing.JTextField();
        txt_answer = new javax.swing.JTextField();
        lbl_question1 = new javax.swing.JLabel();

        lbl_header.setFont(new java.awt.Font("Dialog", 1, 18));
        lbl_header.setText("Edit Dataset");

        lbl_question.setText("Question:");

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

        lbl_question1.setText("Answer:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_header)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_question)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_question, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_edit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cancel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbl_question1, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(txt_answer, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_header)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_question)
                    .addComponent(txt_question, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_answer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_question1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_edit)
                    .addComponent(btn_cancel))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
		if(this.editDataset()) {
			this.parent.refresh();
			this.toggleVisibility();
		} else {
			this.toggleVisibility();
		}
		
	}//GEN-LAST:event_btn_editActionPerformed

	private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
		this.toggleVisibility();
	}//GEN-LAST:event_btn_cancelActionPerformed

	private boolean editDataset() {
		String sql_update =
			"update pool set question = '" + txt_question.getText().trim() +
			"', answer = '" + txt_answer.getText().trim() +
			"' where question = '" + this.question +
			"' and answer = '" + this.answer + "'" +
			" and exerciseareaid = " + this.exerciseid;
		
		if(connection.updateDB(sql_update) == 1) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void toggleVisibility() {
		super.toggleVisibility();
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_edit;
    private javax.swing.JLabel lbl_header;
    private javax.swing.JLabel lbl_question;
    private javax.swing.JLabel lbl_question1;
    private javax.swing.JTextField txt_answer;
    private javax.swing.JTextField txt_question;
    // End of variables declaration//GEN-END:variables
}
