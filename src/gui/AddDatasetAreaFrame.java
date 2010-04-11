package gui;

import data.DBConnection;

public class AddDatasetAreaFrame extends Frame {

	private DBConnection connection;
	private String areaname;
	private EditFrame parent;

	public AddDatasetAreaFrame(DBConnection connection, EditFrame parent, String areaname) {
		initComponents();

		this.connection = connection;
		this.areaname = areaname;

		this.parent = parent;
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                lbl_header = new javax.swing.JLabel();
                lbl_question = new javax.swing.JLabel();
                btn_add = new javax.swing.JButton();
                btn_cancel = new javax.swing.JButton();
                txt_question = new javax.swing.JTextField();
                txt_answer = new javax.swing.JTextField();
                lbl_question1 = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                lbl_header.setFont(new java.awt.Font("Dialog", 1, 18));
                lbl_header.setText("Add new Dataset");

                lbl_question.setText("Question:");

                btn_add.setText("Add");
                btn_add.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_addActionPerformed(evt);
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
                                                .addComponent(btn_add)
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
                                        .addComponent(btn_add)
                                        .addComponent(btn_cancel))
                                .addContainerGap(19, Short.MAX_VALUE))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

	private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
		this.insertDataset();
		this.toggleVisibility();
	}//GEN-LAST:event_btn_addActionPerformed

	private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
		this.toggleVisibility();
	}//GEN-LAST:event_btn_cancelActionPerformed

	public boolean insertDataset() {
		String question = txt_question.getText().trim();
		String answer = txt_answer.getText().trim();

		String sql_insert = String.format("insert into pool(exerciseareaid, question, answer) " +
				    "values((select id from exercisearea where " +
				    "exercisearea.areaname = '%s'), '%s', '%s')",
				    this.areaname, question, answer);

		if(connection.updateDB(sql_insert) == 1)
			return true;
		else
			return false;
	}

	@Override
	public void toggleVisibility() {
		/* Clean the text boxes */
		this.txt_answer.setText("");
		this.txt_question.setText("");

		parent.refresh();
		super.toggleVisibility();
	}
        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btn_add;
        private javax.swing.JButton btn_cancel;
        private javax.swing.JLabel lbl_header;
        private javax.swing.JLabel lbl_question;
        private javax.swing.JLabel lbl_question1;
        private javax.swing.JTextField txt_answer;
        private javax.swing.JTextField txt_question;
        // End of variables declaration//GEN-END:variables
}
