/*
 * Frame.java, package: gui
 * Extends JFrame and offers the base class for our frames.
 */

package gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.UIManager;

import helper.*;

public class Frame extends JFrame {
	public Frame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(500, 350));
		this.setVisible(true);

		/*
		 * Get the system style und try to use it for the frames.
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			Messages.showWarning("Error while setting LookAndFeel:\n"
				+ e.getMessage());
		}
	}
}
