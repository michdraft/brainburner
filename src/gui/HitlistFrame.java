package gui;

import data.DBConnection;
import data.objects.HitlistTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JLabel;

public class HitlistFrame extends Frame {
	
	private DBConnection connection;
	private HitlistTable hitlist;
	private ArrayList<Object[]> list;

	public HitlistFrame(DBConnection connection, ArrayList<Object[]> list) {
		this.connection = connection;
		this.list = list;
		hitlist = new HitlistTable(connection, this.list);
		
		this.setMinimumSize(new Dimension(600, 400));
		this.setTitle("Hitlist");
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		this.north();
		this.add(hitlist, BorderLayout.CENTER);


		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				toggleVisibility();
			}
		});
	}

	private void north() {

		Box firstRow = Box.createHorizontalBox();

		JLabel header = new JLabel("- Trefferliste -");
		header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

		firstRow.add(Box.createHorizontalGlue());
		firstRow.add(header);
		firstRow.add(Box.createHorizontalGlue());
		firstRow.setOpaque(true);
		firstRow.setBackground(Color.lightGray);

		this.add(firstRow, BorderLayout.NORTH);
	}

	@Override
	public void toggleVisibility() {
		super.toggleVisibility();
	}
}
