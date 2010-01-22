/*
 * Messages.java, Package: helper
 * Several functions for displaying messages to the user.
 */

package helper;

import javax.swing.JOptionPane;

public class Messages {
	/**
	 * Shows an info dialog
	 * @param str String to display
	 */
	public static void showInfo(String str) {
		JOptionPane.showMessageDialog(null, str, "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Shows a warning dialog
	 * @param str String to display
	 */
	public static void showWarning(String str) {
		JOptionPane.showMessageDialog(null, str, "Warning", JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Shows an error dialog
	 * @param str String to display
	 */
	public static void showError(String str) {
		JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
