package gui;

import data.DBConnection;
import data.ExerciseAreas;
import data.Statistics;
import data.Users;
import data.objects.Statistic;
import data.objects.StatisticTable;
import data.objects.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JLabel;

public class StatisticFrame extends Frame {
	
	private DBConnection connection;
	private StatisticTable statistic;
	private User user;

	public StatisticFrame(DBConnection connection, User user) {
		this.user = user;
		this.connection = connection;
		statistic = new StatisticTable(connection, getData());
		
		this.setMinimumSize(new Dimension(600, 400));
		this.setTitle("Hitlist");
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		this.north();
		this.add(statistic, BorderLayout.CENTER);


		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				toggleVisibility();
			}
		});
	}

	private void north() {
		Box firstRow = Box.createHorizontalBox();

		JLabel header = new JLabel("- Statistic -");
		header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

		firstRow.add(Box.createHorizontalGlue());
		firstRow.add(header);
		firstRow.add(Box.createHorizontalGlue());
		firstRow.setOpaque(true);
		firstRow.setBackground(Color.lightGray);

		this.add(firstRow, BorderLayout.NORTH);
	}

	private ArrayList<Object[]> getData() {
		return Statistics.getAllStatisticsFromUser(connection, user);
	}
}
