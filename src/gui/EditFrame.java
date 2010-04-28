package gui;

import data.DBConnection;
import data.objects.OverviewExTable;
import helper.Helpers;
import helper.Messages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class EditFrame extends Frame {
	
	private DBConnection connection;
	private String areaname;
	private int exerciseid;
	private OverviewExTable overview;
	private AddDatasetAreaFrame addDatasetAreaFrame;
	private MainFrame parent;
	private JTextField txt_search;

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
			@Override
			public void windowClosing(WindowEvent e) {
				toggleVisibility();
			}
		});
	}

	private void north() {

		Box north = Box.createVerticalBox();
		Box firstRow = Box.createHorizontalBox();
		Box secondRow = Box.createHorizontalBox();

		Icon icon = new ImageIcon("data/pics/brainburner.png");
		JLabel headline = new JLabel(icon);

		firstRow.add(headline);
		firstRow.add(Box.createHorizontalGlue());
		firstRow.setOpaque(true);
		firstRow.setBackground(Color.lightGray);

		north.add(firstRow);

		JToolBar toolbar = new JToolBar();
		toolbar.setPreferredSize(new Dimension(800, 30));

		JLabel lbl_search = new JLabel("Search:");

		txt_search = new JTextField();
		txt_search.setPreferredSize(new Dimension(120, toolbar.getPreferredSize().height-10));
		txt_search.setMaximumSize(new Dimension(120, toolbar.getPreferredSize().height-10));

		txt_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchWord();
			}
		});

		final Icon icon_search = new ImageIcon("data/icons/search.png");
		JLabel lbl_arrows = new JLabel(icon_search);

		lbl_arrows.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchWord();
			}
		});

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

		Action act_close = new AbstractAction() {
			{
				putValue(Action.NAME, "Close Window");
//				putValue(Action.SMALL_ICON, icon_add);
			}

			public void actionPerformed(ActionEvent e) {
				toggleVisibility();
			}
		};

		toolbar.add(act_addDataset);
		toolbar.add(act_close);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(lbl_search);
		toolbar.add(Box.createHorizontalStrut(5));
		toolbar.add(txt_search);
		toolbar.add(Box.createHorizontalStrut(3));
		toolbar.add(lbl_arrows);
		toolbar.add(Box.createHorizontalStrut(3));
		secondRow.add(toolbar);
		toolbar.setFloatable(false);

		north.add(secondRow);
		this.add(north, BorderLayout.NORTH);
	}

	public void searchWord() {
		if(this.checkSearchfield()) {			
			String input = txt_search.getText().trim().toLowerCase();
			ArrayList<Object[]> list = new ArrayList<Object[]>();

			String sql_select = "select question, answer " +
				"from pool where exerciseareaid = " + this.exerciseid +
				" and(lower(question) like '" + input + "'" +
				" or lower(question) like '%" + input + " %'" +
				" or lower(question) like '% " + input + "%'" +
				" or lower(answer) like '" + input + "'" +
				" or lower(answer) like '%" + input + " %'" +
				" or lower(answer) like '% " + input + "%')";

			ResultSet result_set = connection.queryDB(sql_select);

			try {
				while (result_set.next()) {
					Object[] obj = new Object[2];
					obj[0] = result_set.getString("question");
					obj[1] = result_set.getString("answer");
					list.add(obj);
				}
			} catch (SQLException e) {
				Helpers.debug("select: Error: %s\n", e.getMessage());
			}
			if(checkHits(list)) {
				txt_search.setText("");
				new HitlistFrame(connection, list).toggleVisibility();
			} else {
				Messages.showInfo("No matches found!");
			}
		} else {
			Messages.showInfo("Keyword is missing!");
		}
	}

	private boolean checkSearchfield() {
		if(txt_search.getText().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkHits(ArrayList<Object[]> list) {
		if(list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public void refresh() {
		overview.refresh();
	}

	@Override
	public void toggleVisibility() {
		this.parent.refresh();
		this.parent.edit_frame_lock = false;
		super.toggleVisibility();
	}

}
