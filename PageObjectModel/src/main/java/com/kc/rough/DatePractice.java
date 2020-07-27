package com.kc.rough;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePractice {

	public static void main(String[] args) {

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("d-MMM-yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, 20);
		String output = sdf.format(c.getTime());
		
		System.out.println(output);
	}

}
