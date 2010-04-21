package gui;

import data.DBConnection;
import data.objects.OverviewExTable;
import helper.Messages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	private int exerciseid;
	private OverviewExTable overview;
	private AddDatasetAreaFrame addDatasetAreaFrame;
	private MainFrame parent;

	public EditFrame(DBConnection connection, MainFrame parent, String areaname, int exerciseid) {
		this.parent = parent;
		this.connection = connection;
		this.areaname = areaname;
		this.exerciseid = exerciseid;
		addDatasetAreaFrame = new AddDatasetAreaFrame(connection, this, areaname);
		overview = new OverviewExTable(connection, this.areaname, this.exerciseid);
		
		this.setMinimumSize(new Dimension(800, 600));
		this.setTitle("Window for editing a Learn Table");
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		this.north();
		this.add(overview, BorderLayout.CENTER);


		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				toggleVisibility();
			}
		});
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
				addDatasetAreaFrame.toggleVisibility();
			}
		};

		toolbar.add(act_addDataset);
		secondRow.add(toolbar);
		toolbar.setFloatable(false);
		toolbar.add(Box.createHorizontalGlue());

		north.add(secondRow);
		this.add(north, BorderLayout.NORTH);
	}

	public void refresh() {
		overview.refresh();
	}

	@Override
	public void toggleVisibility() {
		this.parent.refresh();
		super.toggleVisibility();
	}
}
