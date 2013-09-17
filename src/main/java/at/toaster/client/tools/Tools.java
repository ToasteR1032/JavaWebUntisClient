package at.toaster.client.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
	
	@SuppressWarnings("deprecation")
	public static Date convertToDate(Date day, String time) {
		
		int min = Integer.parseInt(time.substring(time.length() - 2, time.length()));
		int hour = Integer.parseInt(time.substring(0, time.length() - 2));
		
		day.setMinutes(min);
		day.setHours(hour);
		day.setSeconds(0);
		
		return new Date(day.getTime());
		
	}
	
	public static String convertToString(Date d) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		return sdf.format(d);
		
	}
	
}
