package at.toaster.client.data;

import java.util.Comparator;

import at.toaster.client.data.webuntis.TimeTableField;

public class TimeTableFieldSorter implements Comparator<TimeTableField> {

	public int compare(TimeTableField ttf1, TimeTableField ttf2) {
		//System.out.println(ttf1.getStart().toLocaleString() + " " + ttf2.getStart().toLocaleString());
		//System.out.println(ttf1.getStart().compareTo(ttf2.getStart()));
		return ttf1.getStart().compareTo(ttf2.getStart());
	}

}
