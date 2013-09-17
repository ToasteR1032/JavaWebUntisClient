package at.toaster.client.data.webuntis;

import java.util.ArrayList;
import java.util.Date;

public class TimeTableField {
	
	private ArrayList<Lesson> lessons;
	private Date start;
	private Date end;
	
	public TimeTableField(ArrayList<Lesson> lessons, Date start, Date end) {
		
		this.lessons = lessons;
		this.start = start;
		this.end = end;
		
	}
	
	public ArrayList<Lesson> getLessons() {
		return this.lessons;
	}
	
	public Date getStart() {
		return this.start;
	}
	
	public Date getEnd() {
		return this.end;
	}
	
}
