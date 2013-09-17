package at.toaster.client.data;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import at.toaster.client.data.webuntis.Code;
import at.toaster.client.data.webuntis.Day;
import at.toaster.client.data.webuntis.Grade;
import at.toaster.client.data.webuntis.Lesson;
import at.toaster.client.data.webuntis.Room;
import at.toaster.client.data.webuntis.Subject;
import at.toaster.client.data.webuntis.Teacher;
import at.toaster.client.data.webuntis.TimeTable;
import at.toaster.client.data.webuntis.TimeTableField;
import at.toaster.client.data.webuntis.Type;
import at.toaster.client.data.webuntis.Unit;
import at.toaster.client.tools.Tools;

public class WebUntisConnector {

	private String school;
	private String username;
	private String password;

	private URL url;
	private String sessionID;

	public WebUntisConnector(String address, String school, String username,
			String password) throws JSONException, IOException {

		this.school = school;
		this.username = username;
		this.password = password;

		this.url = new URL(address + "jsonrpc.do?school=" + this.school);

		this.sessionID = WebUntisRequests.getSessionID(this.url, this.username,
				this.password);

	}

	public ArrayList<Grade> getGradeList() throws JSONException, IOException {

		ArrayList<Grade> gradelist = new ArrayList<Grade>();

		JSONObject jsonin = new JSONObject();
		jsonin.put("method", "getKlassen");
		jsonin.put("jsonrpc", "2.0");
		jsonin.put("id", "2");

		JSONObject jsonout = WebUntisRequests.getData(this.url, this.sessionID,
				jsonin);

		JSONArray arr = jsonout.getJSONArray("result");

		for (int i = 0; i < arr.length(); i++) {

			JSONObject o = new JSONObject(arr.get(i).toString());

			Grade g = new Grade(o.getInt("id"), o.getString("name"),
					o.getString("longName"), o.getString("did"));

			gradelist.add(g);

		}

		return gradelist;

	}

	public ArrayList<Room> getRoomList() throws JSONException, IOException {

		ArrayList<Room> roomlist = new ArrayList<Room>();

		JSONObject jsonin = new JSONObject();
		jsonin.put("method", "getRooms");
		jsonin.put("jsonrpc", "2.0");
		jsonin.put("id", "2");

		JSONObject jsonout = WebUntisRequests.getData(this.url, this.sessionID,
				jsonin);

		JSONArray arr = jsonout.getJSONArray("result");

		for (int i = 0; i < arr.length(); i++) {

			JSONObject o = new JSONObject(arr.get(i).toString());

			Room r = new Room(o.getInt("id"), o.getString("name"),
					o.getString("longName"));

			roomlist.add(r);

		}

		return roomlist;

	}

	public ArrayList<Teacher> getTeacherList() throws JSONException,
			IOException {

		ArrayList<Teacher> teacherlist = new ArrayList<Teacher>();

		JSONObject jsonin = new JSONObject();
		jsonin.put("method", "getTeachers");
		jsonin.put("jsonrpc", "2.0");
		jsonin.put("id", "2");

		JSONObject jsonout = WebUntisRequests.getData(this.url, this.sessionID,
				jsonin);

		JSONArray arr = jsonout.getJSONArray("result");

		for (int i = 0; i < arr.length(); i++) {

			JSONObject o = new JSONObject(arr.get(i).toString());

			JSONArray jsondids = new JSONArray(o.getString("dids"));

			ArrayList<Integer> dids = new ArrayList<Integer>();

			for (int j = 0; j < jsondids.length(); j++) {

				JSONObject o2 = new JSONObject(jsondids.get(j).toString());

				dids.add(o2.getInt("id"));

			}

			Teacher t = new Teacher(o.getInt("id"), dids, o.getString("name"),
					o.getString("longName"), o.getString("foreName"));

			teacherlist.add(t);

		}

		return teacherlist;

	}

	public ArrayList<Subject> getSubjectList() throws JSONException,
			IOException {

		ArrayList<Subject> subjectlist = new ArrayList<Subject>();

		JSONObject jsonin = new JSONObject();
		jsonin.put("method", "getSubjects");
		jsonin.put("jsonrpc", "2.0");
		jsonin.put("id", "2");

		JSONObject jsonout = WebUntisRequests.getData(this.url, this.sessionID,
				jsonin);

		JSONArray arr = jsonout.getJSONArray("result");

		for (int i = 0; i < arr.length(); i++) {

			JSONObject o = new JSONObject(arr.get(i).toString());

			Subject s = new Subject(o.getInt("id"), o.getString("name"),
					o.getString("longName"));

			subjectlist.add(s);

		}

		return subjectlist;

	}

	@SuppressWarnings("deprecation")
	public TimeTable getTimeTable(Type type, int id, Date start, Date end)
			throws JSONException, IOException {

		long startDateMilSec = start.getTime();
		long endDateMilSec = end.getTime();
		
		ArrayList<Day> daylist = new ArrayList<Day>();
		
		for (long d = startDateMilSec; d <= endDateMilSec; d = d + 86400000) {
			
			Date curr = new Date(d);
			
			curr.setHours(0);
			curr.setMinutes(0);
			curr.setSeconds(0);
			
			JSONObject params = new JSONObject();
			params.put("id", id);
			params.put("type", type.getTypeID());

			params.put("startDate", Tools.convertToString(curr));
			params.put("endDate", Tools.convertToString(curr));

			JSONObject jsonin = new JSONObject();
			jsonin.put("method", "getTimetable");
			jsonin.put("jsonrpc", "2.0");
			jsonin.put("id", "2");
			jsonin.put("params", params);

			JSONObject jsonout = WebUntisRequests.getData(this.url,
					this.sessionID, jsonin);
			
			JSONArray arr = jsonout.getJSONArray("result");

			ArrayList<Teacher> tel = this.getTeacherList();
			ArrayList<Subject> sul = this.getSubjectList();
			ArrayList<Room> rol = this.getRoomList();

			ArrayList<Lesson> lessonlist = new ArrayList<Lesson>();

			for (int i = 0; i < arr.length(); i++) {

				JSONObject o = arr.getJSONObject(i);

				String startTime = o.getString("startTime");
				String endTime = o.getString("endTime");

				String code = "normal";
				if (o.has("code"))
					code = o.getString("code");

				ArrayList<Room> roomlist = new ArrayList<Room>();
				JSONArray roarr = o.getJSONArray("ro");
				for (int j = 0; j < roarr.length(); j++) {
					for (Room r : rol) {
						if (r.getID() == roarr.getJSONObject(j).getInt("id")) {
							roomlist.add(r);
						}
					}
				}

				ArrayList<Subject> subjectlist = new ArrayList<Subject>();
				JSONArray suarr = o.getJSONArray("su");
				for (int j = 0; j < suarr.length(); j++) {
					for (Subject s : sul) {
						if (s.getID() == suarr.getJSONObject(j).getInt("id")) {
							subjectlist.add(s);
						}
					}
				}

				ArrayList<Teacher> teacherlist = new ArrayList<Teacher>();
				JSONArray tearr = o.getJSONArray("te");
				for (int j = 0; j < tearr.length(); j++) {
					for (Teacher t : tel) {
						if (t.getID() == tearr.getJSONObject(j).getInt("id")) {
							teacherlist.add(t);
						}
					}
				}

				Code c = null;

				if (code.equals("normal"))
					c = Code.NORMAL;
				if (code.equals("irrgegular"))
					c = Code.IRREGULAR;
				if (code.equals("cancelled"))
					c = Code.CANCELLED;
				
				lessonlist.add(new Lesson(new Unit(type), teacherlist, subjectlist, roomlist, Tools.convertToDate(curr, startTime), Tools.convertToDate(curr, endTime), c));
				
			}
			
			ArrayList<TimeTableField> ttfs = new ArrayList<TimeTableField>();
			
			ArrayList<Lesson> used = new ArrayList<Lesson>();

			for (Lesson l : lessonlist) {
			
				if (used.contains(l)) continue;
				
				ArrayList<Lesson> used2 = new ArrayList<Lesson>();
				
				ArrayList<Lesson> temp = new ArrayList<Lesson>();
				
				temp.add(l);
				used.add(l);
				
				for (Lesson l2 : lessonlist) {
					
					if (used.contains(l2)) continue;
					if (used2.contains(l2)) continue;
					
					if ((l2.getStart() == l.getStart()) && (l2.getEnd() == l.getEnd())) {
						
						temp.add(l2);
						used2.add(l2);
						
					}
					
				}
				
				ttfs.add(new TimeTableField(temp, l.getStart(), l.getEnd()));
				
			}
			
			TimeTableFieldSorter ttfsorter = new TimeTableFieldSorter();
			Collections.sort(ttfs, ttfsorter);
			
			daylist.add(new Day(curr, ttfs));
			
		}
		
		return new TimeTable(daylist);

	}

}
