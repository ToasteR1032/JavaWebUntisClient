package at.toaster.client.data.webuntis;

import java.util.ArrayList;
import java.util.Date;

public class Day {
	
	private Date date;
	private ArrayList<TimeTableField> ttfs;
	
	public Day(Date date, ArrayList<TimeTableField> ttfs) {
		
		this.date = date;
		this.ttfs = ttfs;
		
	}
	
	public Date getDate() {
		
		return this.date;
		
	}
	
	public ArrayList<TimeTableField> getTimeTableFields() {
		
		return this.ttfs;
		
	}
	
}
