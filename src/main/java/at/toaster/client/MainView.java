package at.toaster.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import at.toaster.client.data.WebUntisConnector;
import at.toaster.client.data.webuntis.Day;
import at.toaster.client.data.webuntis.Lesson;
import at.toaster.client.data.webuntis.TimeTable;
import at.toaster.client.data.webuntis.TimeTableField;

@SuppressWarnings("unused")
public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MainModel mModel;
	private MainControl mControl;
	private JPanel calendar;
	private JLabel lblPlaceholder;
	private JPanel table;
	private JLabel[] labels;

	private WebUntisConnector conn;
	private TimeTable timetable;
	
	private int numberOfLessons;
	
	@SuppressWarnings("deprecation")
	public MainView(MainModel mModel, MainControl mControl, WebUntisConnector conn) {
		super("Stundenplan");
		
		this.conn = conn;
		
		Date start = new Date();
		Date end = new Date();
		start.setDate(16);
		end.setDate(20);

		try {
			this.timetable = this.conn.getTimeTable(at.toaster.client.data.webuntis.Type.CLASS, 591, start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (Day d : timetable.getDays()) {
			
			int i = d.getTimeTableFields().size();
			
			if (i > this.numberOfLessons) this.numberOfLessons = i;
			
		}
		
		this.mModel = mModel;
		this.mControl = mControl;
		initGUI();
	}

	private void initGUI() {
		getContentPane().setLayout(new BorderLayout());
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Border b = BorderFactory.createLineBorder(Color.black);
		Font weekDays = new Font(null, Font.PLAIN, 25);
		
		this.calendar = new JPanel();
		this.calendar.setLayout(new GridLayout(2, 1));
		
		this.lblPlaceholder = new JLabel("Placeholder");
		this.lblPlaceholder.setBorder(b);
		
		this.table = new JPanel();
		this.table.setLayout(new GridLayout(5, (this.numberOfLessons + 1)));
		
		for (int i = 0; i < timetable.getDays().size(); i++) {
			
			Day d = timetable.getDays().get(i);
			
			if (i == 0) {
				JLabel l = new JLabel("Mo", JLabel.CENTER);
				l.setFont(weekDays);
				l.setBorder(b);
				table.add(l);
			} else if (i == 1) {
				JLabel l = new JLabel("Di", JLabel.CENTER);
				l.setFont(weekDays);
				l.setBorder(b);
				table.add(l);
			} else if (i == 2) {
				JLabel l = new JLabel("Mi", JLabel.CENTER);
				l.setFont(weekDays);
				l.setBorder(b);
				table.add(l);
			} else if (i == 3) {
				JLabel l = new JLabel("Do", JLabel.CENTER);
				l.setFont(weekDays);
				l.setBorder(b);
				table.add(l);
			} else if (i == 4) {
				JLabel l = new JLabel("Fr", JLabel.CENTER);
				l.setFont(weekDays);
				l.setBorder(b);
				table.add(l);
			}
			
			for (int j = 0; j < d.getTimeTableFields().size(); j++) {

				TimeTableField ttf = d.getTimeTableFields().get(j);
				
				JLabel l = new JLabel("<html>" + ttf.getLessons().get(0).getTeacherList().get(0).getName() + "<br>" + ttf.getLessons().get(0).getSubjectList().get(0).getName() + " " + ttf.getLessons().get(0).getRoomList().get(0).getName() + "</html>", JLabel.CENTER);
				l.setBorder(b);
				table.add(l);
			}
			
			for (int j = 0; j < (this.numberOfLessons - d.getTimeTableFields().size()); j++) {
				
				JLabel l = new JLabel();
				l.setBorder(b);
				table.add(l);
				
			}
			
		}
		
		this.calendar.add(this.lblPlaceholder);
		this.getContentPane().add(this.table, BorderLayout.CENTER);
		this.getContentPane().add(this.calendar, BorderLayout.WEST);
		
		this.setVisible(true);
	}

}
