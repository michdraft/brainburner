package gui;

import data.DBConnection;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LanguageFrame extends Frame {

	/* XXX FIXME XXX */
	private JLabel lbl_header, lbl_languagename;
	private JButton btn_ok, btn_cancel;
	private JTextField txt_languagename;

	public LanguageFrame(DBConnection connection) {
		lbl_header		= new JLabel("New Language");
		lbl_languagename	= new JLabel("Language");

		txt_languagename	= new JTextField();

		btn_ok			= new JButton("OK");
		btn_cancel		= new JButton("Cancel");

		this.setLayout(new BorderLayout());
		this.add(lbl_header, BorderLayout.NORTH);
		this.add(lbl_languagename, BorderLayout.CENTER);
		this.add(txt_languagename, BorderLayout.CENTER);
		this.add(btn_ok, BorderLayout.SOUTH);
		this.add(btn_cancel, BorderLayout.SOUTH);
		this.pack();
	}
	/* XXX FIXME XXX */
}
