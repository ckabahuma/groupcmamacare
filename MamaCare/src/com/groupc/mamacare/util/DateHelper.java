package com.groupc.mamacare.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

/**
 * This is the helper date class for mamacare
 * 
 * @author gorishaba
 * 
 */
@SuppressLint("SimpleDateFormat")
public class DateHelper {

	@SuppressLint("SimpleDateFormat")
	public static CharSequence getDate(Date uploadDate) {
		DateFormat outputFormatter = new SimpleDateFormat("dd-MM-yyyy");
		String output = outputFormatter.format(uploadDate);
		return output;
	}

	public static Date getConvertedDate(String string) {
		Date date = new Date();
		SimpleDateFormat sDFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sDFormat.parse(string);
		} catch (ParseException e) {
		}
		return date;
	}

	public static String GetConvertedString(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

}
