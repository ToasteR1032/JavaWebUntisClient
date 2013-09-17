package at.toaster.client.data.webuntis;

import java.util.ArrayList;

public class Teacher {
	private int id;
	private ArrayList<Integer> dids;
	private String name;
	private String longname;
	private String forename;
	
	public Teacher(int id, ArrayList<Integer> dids, String name, String longname, String forename) {

		this.id = id;
		this.dids = dids;
		this.name = name;
		this.longname = longname;
		this.forename = forename;

	}

	public int getID() {
		return this.id;
	}
	
	public ArrayList<Integer> getDIDS() {
		return this.dids;
	}

	public String getName() {
		return this.name;
	}

	public String getLongName() {
		return this.longname;
	}

	public String getForeName() {
		return this.forename;
	}
}
