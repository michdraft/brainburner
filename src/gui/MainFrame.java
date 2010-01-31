package gui;

import data.objects.LearnTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

public class MainFrame extends Frame {

    public MainFrame() {

        this.setMinimumSize(new Dimension(800, 600));
        this.setTitle("BrainBurner");
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        borderLayoutNorth();
        borderLayoutCenter();
        borderLayoutSouth();


    }

    public void borderLayoutNorth() {

        Box northPanel = Box.createVerticalBox();
        Box firstRow = Box.createHorizontalBox();
        Box secondRow = Box.createHorizontalBox();

        Icon icon = new ImageIcon("data/pics/freebsd-devil.png");
        JLabel headline = new JLabel("BrainBurner - Multiuser Lernsoftware", icon, JLabel.LEFT);
        headline.setIconTextGap(20);
        firstRow.add(headline);
        firstRow.add(Box.createHorizontalGlue());
        firstRow.setOpaque(true);
        firstRow.setBackground(Color.LIGHT_GRAY);

        northPanel.add(firstRow);

	JToolBar toolbar = new JToolBar();
	final Icon icon_newlist = new ImageIcon("data/icons/add.png");
	final Icon icon_statistic = new ImageIcon("data/icons/medal_gold_2.png");
	final Icon icon_help = new ImageIcon("data/icons/help.png");

	Action act_newTable = new AbstractAction() {
		{
			putValue(Action.NAME, "New List");
			putValue(Action.SMALL_ICON, icon_newlist);
		}

		public void actionPerformed(ActionEvent e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	};

	Action act_statistic = new AbstractAction() {
		{
			putValue(Action.NAME, "Statistic");
			putValue(Action.SMALL_ICON, icon_statistic);
		}

		public void actionPerformed(ActionEvent e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	};

	Action act_help = new AbstractAction() {
		{
			putValue(Action.NAME, "Help");
			putValue(Action.SMALL_ICON, icon_help);
		}

		public void actionPerformed(ActionEvent e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	};

	toolbar.add(act_newTable);
	toolbar.add(act_statistic);
	toolbar.add(act_help);

	secondRow.add(toolbar);

        /* Add Gap behind the Navigation Buttons */
        secondRow.add(Box.createHorizontalGlue());
        secondRow.setOpaque(true);
        secondRow.setBackground(Color.GRAY);
        northPanel.add(secondRow);

        this.add(northPanel, BorderLayout.NORTH);
    }

    public void borderLayoutCenter() {
        JPanel panel = new JPanel();
        panel.add(new LearnTable());
        this.add(panel, BorderLayout.CENTER);
    }

    public void borderLayoutSouth() {
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel currentUser = new JLabel("current user: admin [Variable]");
        southPanel.add(currentUser, BorderLayout.SOUTH);
        southPanel.setBackground(Color.LIGHT_GRAY);
        this.add(southPanel, BorderLayout.SOUTH);
    }
    
}
