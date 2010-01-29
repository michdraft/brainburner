package gui;

import data.objects.LearnTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;

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

        /* Create Navigation Buttons */
        JButton newTable = new JButton("+ New List");
        JButton statistic = new JButton("Statistik");
        JButton help = new JButton("Help");

        /* Add Navigation Buttons */
        secondRow.add(newTable);
        secondRow.add(statistic);
        secondRow.add(help);

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
