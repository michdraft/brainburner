package gui;

import data.DBConnection;
import data.objects.UserTable;
import helper.Helpers;
import helper.Messages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MainFrame extends Frame {

    String username;
    LoginFrame loginFrame;
    DBConnection connection;

    public MainFrame(DBConnection connection, String username, 
	    LoginFrame lframe) {

	this.connection = connection;
	this.username = username;
	this.loginFrame = lframe;

        this.setMinimumSize(new Dimension(800, 600));
        this.setTitle("BrainBurner");
        this.setLayout(new BorderLayout());
	Helpers.centerWindow(this);

        borderLayoutNorth();
        borderLayoutCenter();
        borderLayoutSouth();

        this.setVisible(true);

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

	northPanel.add(Box.createVerticalStrut(5));
        northPanel.add(firstRow);
	northPanel.add(Box.createVerticalStrut(5));

        /* Create Navigation Buttons */
        JButton newTable = new JButton("+ New List");
	newTable.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new ExerciseTableFrame(connection, username);
		}
	});
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
	
	northPanel.setOpaque(true);
	northPanel.setBackground(Color.LIGHT_GRAY);
        this.add(northPanel, BorderLayout.NORTH);
    }

    public void borderLayoutCenter() {
	Box horizontal = Box.createHorizontalBox();
	Box center = Box.createVerticalBox();
	horizontal.add(Box.createHorizontalStrut(5));
	center.add(Box.createVerticalStrut(5));
	center.add(new UserTable(this.connection));
	center.add(Box.createVerticalStrut(5));
	horizontal.add(center);
	horizontal.add(Box.createHorizontalStrut(5));
        this.add(horizontal, BorderLayout.CENTER);
    }

    public void borderLayoutSouth() {
	Box vert = Box.createVerticalBox();
	Box user = Box.createHorizontalBox();
        JLabel currentUser = new JLabel("current user: "+ this.username);
	JLabel logout = new JLabel("logout");
	logout.setForeground(Color.red);
	logout.addMouseListener(new MouseAdapter() {
			@Override
		public void mouseClicked(MouseEvent e) {
			logout();
		}
	});

	user.add(Box.createHorizontalStrut(3));
	user.add(currentUser);
	user.add(Box.createHorizontalGlue());
	user.add(logout);
	user.add(Box.createHorizontalStrut(2));

	vert.setOpaque(true);
	vert.setBackground(Color.LIGHT_GRAY);
	vert.add(Box.createVerticalStrut(5));
	vert.add(user);
	vert.add(Box.createVerticalStrut(5));
	
        this.add(vert, BorderLayout.SOUTH);
    }

    public void logout()
    {
	this.setVisible(false);
	this.loginFrame.showLoginFrame();
	this.loginFrame.removeLoginMask();
	Messages.showInfo("Logout successful!");
    }    	
    
}
