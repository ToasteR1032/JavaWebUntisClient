package at.toaster.client.data.webuntis;

public class Subject {
	
	private int id;
	private String name;
	private String longname;

	public Subject(int id, String name, String longname) {

		this.id = id;
		this.name = name;
		this.longname = longname;

	}

	public int getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getLongName() {
		return this.longname;
	}
	
}
