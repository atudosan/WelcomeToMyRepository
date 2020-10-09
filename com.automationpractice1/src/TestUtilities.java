package com.herokuapp.theinternet.base;

import java.util.Calendar;

public class TestUtilities extends BaseTest {
	
	
	public static String timeStamp() {
		Calendar cal = Calendar.getInstance();		
		String dateStamp = cal.getTime().toString().replace(":","_").replace(" ", "_");
		return dateStamp;	
		
		
	}

}
