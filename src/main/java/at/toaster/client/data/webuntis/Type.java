package at.toaster.client.data.webuntis;

public enum Type {
	
	CLASS(1), TEACHER(2), SUBJECT(3), ROOM(4), STUDENT(5);
	
	private int typeid;
	
	Type(int typeid) {
		
		this.typeid = typeid;
		
	}
	
	public int getTypeID() {
		
		return this.typeid;
		
	}
	
}
