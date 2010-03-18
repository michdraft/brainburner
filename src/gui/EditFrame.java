package gui;

import data.DBConnection;
import data.objects.OverviewExTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class EditFrame extends Frame {
	
	private DBConnection connection;
	private String areaname;
	private OverviewExTable overview;

	public EditFrame(DBConnection connection, String areaname) {
		this.connection = connection;
		this.areaname = areaname;
		overview = new OverviewExTable(connection, areaname);
		
		this.setMinimumSize(new Dimension(800, 600));
		this.setTitle("Window for editing a Learn Table");
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		this.north();
		this.add(overview, BorderLayout.CENTER);
	}

	private void north() {

		Box north = Box.createVerticalBox();
		Box firstRow = Box.createHorizontalBox();
		Box secondRow = Box.createHorizontalBox();

		Icon icon = new ImageIcon("data/pics/freebsd-devil.png");
		JLabel headline = new JLabel("BrainBurner - Multiuser Lernsoftware", icon, JLabel.LEFT);

		headline.setIconTextGap(20);
		firstRow.add(headline);
		firstRow.add(Box.createHorizontalGlue());
		firstRow.setOpaque(true);
		firstRow.setBackground(Color.lightGray);

		north.add(firstRow);

		JToolBar toolbar = new JToolBar();
		final Icon icon_add = new ImageIcon("data/icons/add.png");

		Action act_addDataset = new AbstractAction() {
			{
				putValue(Action.NAME, "Add Dataset");
				putValue(Action.SMALL_ICON, icon_add);
			}

			public void actionPerformed(ActionEvent e) {
				new AddDatasetAreaFrame(connection, areaname);
			}
		};

		toolbar.add(act_addDataset);
		secondRow.add(toolbar);
		toolbar.setFloatable(false);
		toolbar.add(Box.createHorizontalGlue());

		north.add(secondRow);
		this.add(north, BorderLayout.NORTH);
	}
}
