package at.toaster.client.data.webuntis;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

public class TimeTable {
	
	private ArrayList<Day> days;
	
	public TimeTable(ArrayList<Day> days) throws JSONException, IOException {
		
		this.days = days;
		
	}
	
	public ArrayList<Day> getDays() {
		return this.days;
	}
	
}
