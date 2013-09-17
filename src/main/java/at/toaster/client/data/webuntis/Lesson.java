package at.toaster.client.data.webuntis;

import java.util.ArrayList;
import java.util.Date;

public class Lesson {

	private Unit u;
	private ArrayList<Teacher> tlist;
	private ArrayList<Subject> slist;
	private ArrayList<Room> rlist;
	
	private Date start;
	private Date end;
	
	private Code c;
	
	public Lesson(Unit u, ArrayList<Teacher> tlist, ArrayList<Subject> slist,  ArrayList<Room> rlist, Date start, Date end, Code c) {

		this.u = u;
		this.tlist = tlist;
		this.slist = slist;
		this.rlist = rlist;

		this.end = end;
		this.start = start;
		
		this.c = c;
		
	}
	
	public Unit getUnit() {
		return this.u;
	}
	
	public ArrayList<Teacher> getTeacherList() {
		return this.tlist;
	}
	
	public ArrayList<Subject> getSubjectList() {
		return this.slist;
	}
	
	public ArrayList<Room> getRoomList() {
		return this.rlist;
	}
	
	public Date getStart() {
		return this.start;
	}
	
	public Date getEnd() {
		return this.end;
	}
	
	public Code getCode() {
		return this.c;
	}
	
}
