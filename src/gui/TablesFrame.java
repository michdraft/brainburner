package gui;

import data.objects.*;
import data.DBConnection;
import data.LearnTables;
import helper.Helpers;
import java.sql.ResultSet;

/**
 * Tables.java, package: gui
 * Trivial form to create Language-Usertable relations
 */
public class TablesFrame extends Frame {

	DBConnection connection;

	public TablesFrame(DBConnection connection) {
		initComponents();
		this.connection = connection;

		String query =
			"select * from languages";
		ResultSet result_set = connection.queryDB(query);
		try {
			while (result_set.next()) {
				cb_language.addItem(new Language(result_set.getInt("ID"), result_set.getString("NAME")));
			}
		} catch (Exception e) {
			Helpers.debug("Connection: Error: %s\n", e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                lbl_header = new javax.swing.JLabel();
                txt_name = new javax.swing.JTextField();
                cb_language = new javax.swing.JComboBox();
                lbl_name = new javax.swing.JLabel();
                lbl_language = new javax.swing.JLabel();
                btn_create = new javax.swing.JButton();
                btn_cancel = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                lbl_header.setFont(new java.awt.Font("Dialog", 1, 18));
                lbl_header.setText("Create learn table");

                lbl_name.setText("Name:");

                lbl_language.setText("Language:");

                btn_create.setText("Create");
                btn_create.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_createActionPerformed(evt);
                        }
                });

                btn_cancel.setText("Cancel");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lbl_header))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(lbl_name)
                                                        .addComponent(lbl_language))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(cb_language, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txt_name, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))))
                                .addContainerGap(15, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(99, Short.MAX_VALUE)
                                .addComponent(btn_create)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cancel)
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbl_header)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_name))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cb_language, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_language))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_create)
                                        .addComponent(btn_cancel))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

	private void btn_createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_createActionPerformed
		String name = txt_name.getText();
		int language_id = ((Language)cb_language.getSelectedItem()).getId();
		String language_str = ((Language)cb_language.getSelectedItem()).getName();

		LearnTable learn_table = new LearnTable(language_id, name);
		if (LearnTables.addLearnTable(connection, learn_table)) {
			Helpers.debug("Table '%s' with language '%s' successfully created!\n", name, language_str);
		}
	}//GEN-LAST:event_btn_createActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btn_cancel;
        private javax.swing.JButton btn_create;
        private javax.swing.JComboBox cb_language;
        private javax.swing.JLabel lbl_header;
        private javax.swing.JLabel lbl_language;
        private javax.swing.JLabel lbl_name;
        private javax.swing.JTextField txt_name;
        // End of variables declaration//GEN-END:variables
}
