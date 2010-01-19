/*
 * Messages.java, Package: helper
 * Several functions for displaying messages to the user.
 */

package helper;

import javax.swing.JOptionPane;

public class Messages {
	public static void showInfo(String str) {
		JOptionPane.showMessageDialog(null, str, "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showWarning(String str) {
		JOptionPane.showMessageDialog(null, str, "Warning", JOptionPane.WARNING_MESSAGE);
	}

	public static void showError(String str) {
		JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
