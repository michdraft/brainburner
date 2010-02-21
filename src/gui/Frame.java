package gui;

import javax.swing.JFrame;
import helper.*;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Frame.java, package: gui
 * Extends JFrame and offers the base class for our frames.
 */
public class Frame extends JFrame {
	public Frame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		/*
		 * Get the system style und try to use it for the frames.
		 */
		try {
			//UIManager.setLookAndFeel(new GTKLookAndFeel());
		} catch (Exception e) {
			Messages.showWarning("Error while setting LookAndFeel:\n"
				+ e.getMessage());
		}
	}

	@Override
	public void pack() {
		super.pack();
		this.centerFrame();
	}

	public void centerFrame() {
		Dimension dimension_frame = this.getSize();
		Dimension dimension_screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dimension_screen.width - dimension_frame.width) / 2;
		int y = (dimension_screen.height - dimension_frame.height) / 2;
		this.setLocation(x, y);
	}

	public void toggleVisibility() {
		if (this.isVisible())
			this.setVisible(false);
		else
			this.setVisible(true);
	}
}
