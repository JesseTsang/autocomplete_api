package com.airgraftcodingchallenge.utils;

public class CanadianProvinceUtil {
    public static String getCanadianProvinceAbvr(String str) {
		String returnCode = "";
		if      (str.equals("01")) {
			returnCode = "AB";
		}
		else if (str.equals("02")) {
			returnCode = "BC";
		}
		else if (str.equals("03")) {
			returnCode = "MB";
		}
		else if (str.equals("04")) {
			returnCode = "NB";
		}
		else if (str.equals("05")) {
			returnCode = "NL";
		}
		else if (str.equals("07")) {
			returnCode = "NS";
		}
		else if (str.equals("08")) {
			returnCode = "ON";
		}
		else if (str.equals("09")) {
			returnCode = "PE";
		}
		else if (str.equals("10")) {
			returnCode = "QC";
		}
		else if (str.equals("11")) {
			returnCode = "SK";
		}
		else if (str.equals("12")) {
			returnCode = "YT";
		}
		else if (str.equals("13")) {
			returnCode = "NT";
		}
		else if (str.equals("14")) {
			returnCode = "NU";
		}
		
		return returnCode;
	}   
}
