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

	DBConnection connection;

	public UsersFrame(DBConnection connection) {
		initComponents();
		this.connection = connection;
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

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
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

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1)
                                        .addComponent(lbl_password)
                                        .addComponent(lbl_username)
                                        .addComponent(lbl_password2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txt_password2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_username, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_password, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                                .addContainerGap(65, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(216, Short.MAX_VALUE)
                                .addComponent(btn_create)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cancel)
                                .addContainerGap())
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

		    if (Users.addUser(connection, user)) {
			    Messages.showInfo("User " + username + " succesfuly created!");
			    this.setVisible(false);
		    }
	    }
    }//GEN-LAST:event_btn_createActionPerformed
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
