package calender;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

class Calender {
	
	
	public static String[] weekdays() {

		String[] arr = new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

		return arr;
	}
	
	public static String[] date()
	{
		LocalDate today = LocalDate.now();
		String [] arr = new String[7];
		TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
		int x = 0;
		
		for ( int i = 1; i <= 7; i++) {
			LocalDate week = today.with(fieldISO, i);
			arr[x] = week.toString();
			x++;
		}
		return arr;
	}
}
