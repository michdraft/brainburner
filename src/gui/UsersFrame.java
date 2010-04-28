package gui;

import helper.*;
import data.objects.User;
import data.DBConnection;
import data.Users;

/**
 * Users.java, package: gui
 * Simple form to create users in the database.
 */
public class UsersFrame extends Frame {

	private DBConnection connection;
	private LoginFrame parent;

	public UsersFrame(DBConnection connection, LoginFrame parent) {
		initComponents();
		this.connection = connection;
		this.parent = parent;

		this.getRootPane().setDefaultButton(btn_create);
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabel1 = new javax.swing.JLabel();
                txt_username = new javax.swing.JTextField();
                lbl_username = new javax.swing.JLabel();
                lbl_password = new javax.swing.JLabel();
                lbl_password2 = new javax.swing.JLabel();
                btn_create = new javax.swing.JButton();
                btn_cancel = new javax.swing.JButton();
                txt_password = new javax.swing.JPasswordField();
                txt_password2 = new javax.swing.JPasswordField();

                jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
                jLabel1.setText("Create User");

                lbl_username.setText("Username:");

                lbl_password.setText("Password:");

                lbl_password2.setText("Repeat Password:");

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

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btn_create)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btn_cancel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(lbl_password)
                                                        .addComponent(lbl_username)
                                                        .addComponent(lbl_password2))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(txt_password2, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txt_username, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txt_password, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_username))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl_password)
                                        .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_password2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_password2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_cancel)
                                        .addComponent(btn_create))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btn_createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_createActionPerformed

	    String username = txt_username.getText();
	    char[] password = txt_password.getPassword();
	    char[] password2 = txt_password2.getPassword();


	    if (!Helpers.cmpPasswords(password, password2)) {
		    Messages.showWarning("Passwords do not match!");
	    } else {
		    User user = new User(username, new String(password));
		    user.encryptPassword();

		    int ret = Users.addUser(connection, user);
		    if (ret == Users.STAT_OK) {
			    Messages.showInfo("User " + username + " succesfuly created!");
			    parent.fillLoginMask(username, new String(password));
			    this.setVisible(false);
		    } else if (ret == Users.STAT_NOT_UNIQUE) {
			    Messages.showError("An user with this name already exists!");
			    cleanFields();
		    }
	    }
    }//GEN-LAST:event_btn_createActionPerformed

    private void cleanFields() {
	    txt_username.setText("");
	    txt_password.setText("");
	    txt_password2.setText("");
	    txt_username.requestFocus();
    }

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
	    this.toggleVisibility();
    }//GEN-LAST:event_btn_cancelActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btn_cancel;
        private javax.swing.JButton btn_create;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel lbl_password;
        private javax.swing.JLabel lbl_password2;
        private javax.swing.JLabel lbl_username;
        private javax.swing.JPasswordField txt_password;
        private javax.swing.JPasswordField txt_password2;
        private javax.swing.JTextField txt_username;
        // End of variables declaration//GEN-END:variables
}
