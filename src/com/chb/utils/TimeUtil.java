package com.chb.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	public static String getCurrentTimeString(String format) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
