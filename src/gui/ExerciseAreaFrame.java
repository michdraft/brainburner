package gui;

import data.objects.*;
import data.DBConnection;
import data.ExerciseAreas;
import data.Languages;
import helper.Messages;
// import helper.Helpers;

/**
 * Tables.java, package: gui
 * Trivial form to create Language-Usertable relations
 */
public class ExerciseAreaFrame extends Frame {

	private DBConnection connection;
	private MainFrame parent;
	private String username;
	private AddLanguageFrame addlanguageframe;

	public ExerciseAreaFrame(DBConnection connection, MainFrame parent, String username) {
		initComponents();

		this.parent = parent;
		this.connection = connection;
		this.username = username;
		this.addlanguageframe = new AddLanguageFrame(connection, this);

		this.getRootPane().setDefaultButton(btn_create);

		this.showLanguagesInJComboBox();
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
                lbl_newlanguage = new javax.swing.JLabel();

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
                btn_cancel.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_cancelActionPerformed(evt);
                        }
                });

                lbl_newlanguage.setForeground(java.awt.Color.blue);
                lbl_newlanguage.setText("New language");
                lbl_newlanguage.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lbl_newlanguageMouseClicked(evt);
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
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(lbl_name)
                                                        .addComponent(lbl_language))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(cb_language, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txt_name, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(btn_create)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btn_cancel)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(163, Short.MAX_VALUE)
                                .addComponent(lbl_newlanguage)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_newlanguage)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_create)
                                        .addComponent(btn_cancel))
                                .addContainerGap())
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

	private void btn_createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_createActionPerformed
		String areaname = txt_name.getText().trim();
		String languagename;

		if(areaname.isEmpty()) {
			Messages.showWarning("Don't forget inputting a name!");
		} else {
			if (cb_language.getItemCount() == 0) {
				Messages.showWarning("You have to create a languge first!");
			} else {
				areaname = txt_name.getText().trim();
				languagename = cb_language.getSelectedItem().toString();

				ExerciseArea exercisearea = new ExerciseArea(areaname);

				int ret = ExerciseAreas.addExerciseArea(connection, exercisearea, username, languagename);
				if (ret == ExerciseAreas.STAT_OK) {
					Messages.showInfo("Learn Table " +
						exercisearea + " succesfuly created!");
					this.toggleVisibility();

				} else if (ret == ExerciseAreas.STAT_NOT_UNIQUE) {
					Messages.showError("There is already an area with this name!");
					clearFields();
				}
			}
		}
	}//GEN-LAST:event_btn_createActionPerformed

	private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
		this.toggleVisibility();
	}//GEN-LAST:event_btn_cancelActionPerformed

	private void lbl_newlanguageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_newlanguageMouseClicked
		this.addlanguageframe.toggleVisibility();
	}//GEN-LAST:event_lbl_newlanguageMouseClicked

       /*
	* This method shows all available Languages in the JComboBox.
	*/
	private void showLanguagesInJComboBox() {
		Languages languages = Languages.getAllLanguages(connection);
		for (Language l : languages) {
			cb_language.addItem(l);
		}
	}

	private void clearFields() {
		txt_name.setText("");
		txt_name.requestFocus();
	}

	public void refresh() {
		cb_language.removeAllItems();
		showLanguagesInJComboBox();
	}

	@Override
	public void toggleVisibility() {
		clearFields();
		parent.refresh();
		super.toggleVisibility();
	}
        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btn_cancel;
        private javax.swing.JButton btn_create;
        private javax.swing.JComboBox cb_language;
        private javax.swing.JLabel lbl_header;
        private javax.swing.JLabel lbl_language;
        private javax.swing.JLabel lbl_name;
        private javax.swing.JLabel lbl_newlanguage;
        private javax.swing.JTextField txt_name;
        // End of variables declaration//GEN-END:variables
}
