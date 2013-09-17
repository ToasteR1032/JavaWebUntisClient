package at.toaster.client.data.webuntis;

public class Grade {
	
	private int id;
	private String name;
	private String longname;
	private String did;
	
	public Grade(int id, String name, String longname, String did) {
		
		this.id = id;
		this.name = name;
		this.longname = longname;
		this.did = did;
		
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
	
	public String getDID() {
		return this.did;
	}
	
}
