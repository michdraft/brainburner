/*
 * Frame.java, package: gui
 * Extends JFrame and offers the base class for our frames.
 */

package gui;

import com.sun.java.swing.plaf.gtk.GTKLookAndFeel;
import javax.swing.JFrame;
import javax.swing.UIManager;

import helper.*;

public class Frame extends JFrame {
	public Frame() {
		/*
		 * Get the system style und try to use it for the frames.
		 */
		try {
			UIManager.setLookAndFeel(new GTKLookAndFeel());
		} catch (Exception e) {
			Messages.showWarning("Error while setting LookAndFeel:\n"
				+ e.getMessage());
		}


		this.setVisible(true);
	}
}
