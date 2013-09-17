package at.toaster.client;

import java.util.Date;

import javax.swing.JOptionPane;

import at.toaster.client.data.WebUntisConnector;
import at.toaster.client.data.webuntis.TimeTable;
import at.toaster.client.data.webuntis.Type;

public class Test {

	public static void main(String[] args) {

		new Test();

	}

	public Test() {

		WebUntisConnector conn;

		try {
			long startt = System.currentTimeMillis();

			conn = new WebUntisConnector("https://stpl.tgm.ac.at/WebUntis/",
					"tgm", "rhollander", JOptionPane.showInputDialog(null, "Passwort fuer rhollander"));

			long stopt = System.currentTimeMillis();

			System.out.println("Time to login: " + (stopt - startt));

			Date start = new Date();
			Date end = new Date();

			//end.setDate(18);

			long startt2 = System.currentTimeMillis();

			TimeTable t = conn.getTimeTable(Type.CLASS, 591, start, end);

			long stopt2 = System.currentTimeMillis();

			System.out.println("Time to process data: " + (stopt2 - startt2));

			System.out.println(t.getDays().get(0).getTimeTableFields().get(0)
					.getLessons().get(0).getSubjectList().get(0).getName());

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
