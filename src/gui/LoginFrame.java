package gui;

import data.DBConnection;
import data.Users;
import data.objects.User;
import helper.Helpers;
import helper.Messages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * LoginFrame.java, package: gui
 * Testframe for a login window.
 */
public class LoginFrame extends Frame {

	private DBConnection connection;
	private UsersFrame users_frame;
	private MainFrame main_frame;

	private JLabel lbl_header, lbl_username, lbl_password, lbl_register;
	private JTextField txt_username;
	private JPasswordField pwd_password;
	private JButton btn_ok, btn_cancel, btn_new_user;
	private JPanel pnl_input, pnl_buttons;

	public LoginFrame(final DBConnection connection) {
		this.connection = connection;
		this.users_frame = new UsersFrame(connection);

		lbl_header	= new JLabel("BrainBurner - Login");
		lbl_header.setOpaque(true);
		lbl_header.setBackground(Color.LIGHT_GRAY);
		lbl_header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

		lbl_username	= new JLabel("Username:");
		lbl_username.setBounds(10, 20, 70, 25);
		txt_username	= new JTextField();
		txt_username.setBounds(90, 20, 125, 25);

		lbl_password	= new JLabel("Password:");
		lbl_password.setBounds(10, 50, 70, 25);
		pwd_password	= new JPasswordField();
		pwd_password.setBounds(90, 50, 125, 25);

		lbl_register    = new JLabel("register");
		lbl_register.setForeground(Color.blue);
		lbl_register.setBounds(168, 75, 70, 25);
		lbl_register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showRegisterWindow();
			}
		});

		pnl_input	= new JPanel(null);
		pnl_buttons	= new JPanel(new FlowLayout(FlowLayout.RIGHT));

		btn_ok		= new JButton("Login");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkUser();
			}
		});

		btn_cancel	= new JButton("Cancel");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(240, 190));

		pnl_input.add(lbl_username);
		pnl_input.add(txt_username);
		pnl_input.add(lbl_password);
		pnl_input.add(pwd_password);
		pnl_input.add(lbl_register);

		pnl_buttons.add(btn_ok);
		pnl_buttons.add(btn_cancel);

		this.add(lbl_header, BorderLayout.NORTH);
		this.add(pnl_input, BorderLayout.CENTER);
		this.add(pnl_buttons, BorderLayout.SOUTH);

		this.pack();
	}

	private void checkUser() {
		String username = txt_username.getText();
		String password = new String(pwd_password.getPassword());

		User user = Users.getUser(this.connection, username);

		if (user != null) {
			if (Helpers.cmpPasswords(password, user.getPassword())) {
				this.login(user);
			} else {
				Messages.showError("Invalid password!");
			}
		} else {
			Messages.showError("User " + username + " doesn't exist!");
		}
	}	

	private void showRegisterWindow() {
		new UsersFrame(connection).toggleVisibility();
	}

	/*
	 * This method clears the input and password field
	 */
	public void removeLoginMask() {
		txt_username.setText(""); pwd_password.setText("");
	}

	public void fillLoginMask(String name, String password) {
		txt_username.setText(name); pwd_password.setText(password);
	}

	private void newUser() {
		users_frame.toggleVisibility();
	}

	private void login(User user) {
		main_frame = new MainFrame(connection, user, user.getName());
		main_frame.toggleVisibility();
		this.toggleVisibility();
	}
}
