package Generic_Utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int generateRandomNumber() {
		Random r = new Random();
		int rannum = r.nextInt(1000);
		return rannum;
	}

	public String getSystemCurrentDate() {
		Date dobj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String currentdate = sim.format(dobj);
		return currentdate;
	}

	public String getDateAfterSpecifiedDays(int days) {
		Date dobj = new Date(); // Current date
		Calendar cal = Calendar.getInstance(); // Correct way to use calendar
		cal.setTime(dobj); // Set calendar to current date
		cal.add(Calendar.DAY_OF_MONTH, days); // Add the number of days

		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String date = sim.format(cal.getTime()); // Format the new date
		return date;
	}
}