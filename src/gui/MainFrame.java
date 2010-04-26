package gui;

import data.DBConnection;
import data.objects.OverviewTable;
import data.objects.User;
import helper.Messages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

public class MainFrame extends Frame {
	DBConnection connection;
	User current_user;
	ExerciseAreaFrame tables_frame;
	DelExerciseAreaFrame del_tables_frame;
	EditExerciseAreaFrame edit_table_frame;
	TrainExerciseAreaFrame train_table_frame;
	StatisticFrame statistic_frame;
	JPanel southPanel;
	JLabel currentUser;
	String username;
	OverviewTable overview_table;

	public MainFrame(DBConnection connection, User current_user, String username) {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.connection = connection;
		this.current_user = current_user;
		this.username = current_user.getName();
		tables_frame = new ExerciseAreaFrame(connection, this, username);
		del_tables_frame = new DelExerciseAreaFrame(connection, this, username);
		edit_table_frame = new EditExerciseAreaFrame(connection, this, username);
		train_table_frame = new TrainExerciseAreaFrame(connection, current_user);
		overview_table = new OverviewTable(connection, this.username);
		statistic_frame = new StatisticFrame(connection, current_user);

		this.setMinimumSize(new Dimension(800, 600));
		this.setTitle("BrainBurner");
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		borderLayoutNorth();
		borderLayoutCenter();
		borderLayoutSouth();

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				Object[] options = {"Yes", "No"};
				int result = JOptionPane.showOptionDialog(null, 
					"Do you really want to exit the program?",
					"Programm Exit",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null, options, null);

				if(result == 0) {
					System.exit(0);
				}
			}
		});
	}

	private void borderLayoutNorth() {
		Box northPanel = Box.createVerticalBox();
		Box firstRow = Box.createHorizontalBox();
		Box secondRow = Box.createHorizontalBox();

		// Icon icon = new ImageIcon("data/pics/freebsd-devil.png");
		// JLabel headline = new JLabel("BrainBurner - Multiuser Lernsoftware", icon, JLabel.LEFT);
		// headline.setIconTextGap(20);
		Icon icon = new ImageIcon("data/pics/brainburner.png");
		JLabel headline = new JLabel(icon, JLabel.LEFT);
		firstRow.add(headline);
		firstRow.add(Box.createHorizontalGlue());
		firstRow.setOpaque(true);
		firstRow.setBackground(Color.lightGray);

		northPanel.add(firstRow);

		JToolBar toolbar = new JToolBar();
		toolbar.setPreferredSize(new Dimension(800, 30));

		final Icon icon_newlist = new ImageIcon("data/icons/add.png");
		final Icon icon_edit = new ImageIcon("data/icons/edit.png");
		final Icon icon_delete = new ImageIcon("data/icons/delete.png");
		final Icon icon_exercise = new ImageIcon("data/icons/exercise.png");
		final Icon icon_statistic = new ImageIcon("data/icons/medal_gold_2.png");
		final Icon icon_help = new ImageIcon("data/icons/help.png");

		Action act_newTable = new AbstractAction() {
			{
				putValue(Action.NAME, "New List");
				putValue(Action.SMALL_ICON, icon_newlist);
			}

			public void actionPerformed(ActionEvent e) {
				addExerciseArea();
			}
		};

		Action act_edit = new AbstractAction() {
			{
				putValue(Action.NAME, "Edit List");
				putValue(Action.SMALL_ICON, icon_edit);
			}

			public void actionPerformed(ActionEvent e) {
				editExerciseArea();
			}
		};

		Action act_delTable = new AbstractAction() {
			{
				putValue(Action.NAME, "Delete List");
				putValue(Action.SMALL_ICON, icon_delete);
			}

			public void actionPerformed(ActionEvent e) {
				deleteExerciseArea();
			}
		};

		Action act_exercise = new AbstractAction() {
			{
				putValue(Action.NAME, "Exercise List");
				putValue(Action.SMALL_ICON, icon_exercise);
			}

			public void actionPerformed(ActionEvent e) {
				trainExerciseArea();
			}
		};

		Action act_statistic = new AbstractAction() {
			{
				putValue(Action.NAME, "Statistic");
				putValue(Action.SMALL_ICON, icon_statistic);
			}

			public void actionPerformed(ActionEvent e) {
				showStatisticFrame();
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
		toolbar.add(act_edit);
		toolbar.add(act_delTable);
		toolbar.add(act_exercise);
		toolbar.add(act_statistic);
		toolbar.add(act_help);
		toolbar.setFloatable(false);
		toolbar.add(Box.createHorizontalGlue());

		secondRow.add(toolbar);
		
		northPanel.add(secondRow);
		this.add(northPanel, BorderLayout.NORTH);
	}

	private void borderLayoutCenter() {
		JPanel panel = new JPanel();
		overview_table.setBackground(Color.LIGHT_GRAY);
		panel.add(overview_table);
		this.add(panel, BorderLayout.CENTER);
	}

	private void borderLayoutSouth() {
		southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		currentUser = new JLabel("Logged in as: " + current_user.getName());
		southPanel.add(currentUser, BorderLayout.SOUTH);
		southPanel.setBackground(Color.LIGHT_GRAY);
		this.add(southPanel, BorderLayout.SOUTH);
	}

	public void refresh() {
		overview_table.refresh();
	}

	private void addExerciseArea() {
		tables_frame.toggleVisibility();
		refresh();
	}

	private void deleteExerciseArea() {
		del_tables_frame.refreshCbExerciseArea();

		if(del_tables_frame.checkComboBox()) {
			del_tables_frame.toggleVisibility();
		} else {
			Messages.showInfo("There is no list to delete!");
		}
		
	}

	private void editExerciseArea() {
		edit_table_frame.refreshCbExerciseArea();
		
		if(edit_table_frame.checkComboBox()) {
			edit_table_frame.toggleVisibility();
		} else {
			Messages.showInfo("There is no list to edit!");
		}
	}

	private void trainExerciseArea() {
		train_table_frame.refreshCbExerciseArea();

		if(train_table_frame.checkComboBox()) {
			train_table_frame.toggleVisibility();
		} else {
			Messages.showInfo("There is no list to learn!");
		}
	}

<<<<<<< HEAD
=======
	private void showStatisticFrame() {
		statistic_frame.toggleVisibility();
	}
>>>>>>> origin/master
}
