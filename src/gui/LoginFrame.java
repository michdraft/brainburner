/*
 * LoginFrame.java, package: gui
 * Testframe for a login window.
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends Frame {

	private JLabel lbl_header, lbl_username, lbl_password;
	private JTextField txt_username;
	private JPasswordField pwd_password;
	private JButton btn_ok, btn_cancel;
	private JPanel pnl_input, pnl_buttons;

	public LoginFrame() {
		lbl_header	= new JLabel("BrainBurner - Login");

		lbl_username	= new JLabel("Username:");
		lbl_username.setBounds(10, 20, 70, 25);
		txt_username	= new JTextField();
		txt_username.setBounds(110, 20, 125, 25);

		lbl_password	= new JLabel("Password:");
		lbl_password.setBounds(10, 50, 70, 25);
		pwd_password	= new JPasswordField();
		pwd_password.setBounds(110, 50, 125, 25);

		pnl_input	= new JPanel(null);
		pnl_buttons	= new JPanel(new FlowLayout(FlowLayout.CENTER));

		btn_ok		= new JButton("Login");
		btn_cancel	= new JButton("Cancel");

		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(300, 200));

		pnl_input.add(lbl_username);
		pnl_input.add(txt_username);
		pnl_input.add(lbl_password);
		pnl_input.add(pwd_password);

		pnl_buttons.add(btn_ok);
		pnl_buttons.add(btn_cancel);

		this.add(lbl_header, BorderLayout.NORTH);
		this.add(pnl_input, BorderLayout.CENTER);
		this.add(pnl_buttons, BorderLayout.SOUTH);
		this.pack();
	}
}
