package com.mapping.util;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class Validation {
 
	
	public static boolean isValidGender(String str) {
		if(str.equalsIgnoreCase("male") || str.equalsIgnoreCase("female")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isNameValidation(String str) {

		return (str.matches("^[a-zA-Z]*"));
	}

	public static boolean isValidDate(Date d) {
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		if(date.compareTo(d) < 0) {
			return true; 
		}else {
			return false;
		}
	}

	public static boolean isValidateEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	public static boolean isValidatePassword(String password) {

		boolean aflag = false, dflag = false, cflag = false, lengthFlag = false;

		if (password.length() >= 8)
			lengthFlag = true;

		for (int i = 0; i < password.length(); i++) {
			if ((password.charAt(i) >= 'a' && password.charAt(i) <= 'z')
					|| (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z')) {
				aflag = true;
				break;
			}
		}
		for (int i = 0; i < password.length(); i++) {
			if ((password.charAt(i) >= '0' && password.charAt(i) <= '9')) {
				dflag = true;
				break;
			}
		}

		for (int i = 0; i < password.length(); i++) {
			if ((!(password.charAt(i) >= 'a' && password.charAt(i) <= 'z')
					&& !(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z'))
					&& !((password.charAt(i) >= '0' && password.charAt(i) <= '9'))) {
				cflag = true;
				break;
			}
		}

		if (aflag == true && dflag == true && cflag == true && lengthFlag == true)
			return true;
		else
			return false;

	}
}
