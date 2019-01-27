package com.educorp.eduinteractive.ecommerce.model.util;

import java.text.DateFormat;
import java.util.Date;

public class DateUtils {
	
	private static final DateFormat MESSAGE_DATE_FORMAT = 
			DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
	
	public static String format(Date d) {
		return MESSAGE_DATE_FORMAT.format(d);
	}
}
