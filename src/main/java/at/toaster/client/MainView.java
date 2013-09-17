package at.toaster.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("unused")
public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MainModel mModel;
	private MainControl mControl;
	private JPanel calendar;
	private JLabel lblPlaceholder;
	private JPanel table;
	private JLabel[] labels;
	private int numberOfLessons;

	public MainView(MainModel mModel, MainControl mControl) {
		super("Stundenplan");
		this.mModel = mModel;
		this.mControl = mControl;
		numberOfLessons = 10; //TODO
		initGUI();
	}

	private void initGUI() {
		getContentPane().setLayout(new BorderLayout());
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.calendar = new JPanel();
		getContentPane().add(this.calendar, BorderLayout.WEST);
		this.calendar.setLayout(new GridLayout(2, 1));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.lblPlaceholder = new JLabel("Placeholder");
		this.calendar.add(this.lblPlaceholder);

		this.table = new JPanel();
		
		this.labels = new JLabel[5 * numberOfLessons + 5];
		Border b = BorderFactory.createLineBorder(Color.black);
		Font weekDays = new Font(null, Font.PLAIN, 25);
		for (int i = 0; i < 5 * numberOfLessons + 5; i++) {

			if (i == 0) {
				labels[i] = new JLabel("Mo", JLabel.CENTER);
				labels[i].setFont(weekDays);
			} else if (i == numberOfLessons+1) {
				labels[i] = new JLabel("Di", JLabel.CENTER);
				labels[i].setFont(weekDays);
			} else if (i == numberOfLessons*2+2) {
				labels[i] = new JLabel("Mi", JLabel.CENTER);
				labels[i].setFont(weekDays);
			} else if (i == numberOfLessons*3+3) {
				labels[i] = new JLabel("Do", JLabel.CENTER);
				labels[i].setFont(weekDays);
			} else if (i == numberOfLessons*4+4) {
				labels[i] = new JLabel("Fr", JLabel.CENTER);
				labels[i].setFont(weekDays);
			} else {
				labels[i] = new JLabel("Space", JLabel.CENTER);
			}
			labels[i].setBorder(b);
			this.table.add(labels[i]);
		}

		this.lblPlaceholder.setBorder(b);
		getContentPane().add(this.table, BorderLayout.CENTER);
		
		((FlowLayout) this.table.getLayout()).setVgap(0);
		((FlowLayout) this.table.getLayout()).setHgap(0);
		
		GridLayout g = new GridLayout(5, 12, 0, 0);
		
		this.table.setLayout(g);
		
		this.setVisible(true);
	}

}
