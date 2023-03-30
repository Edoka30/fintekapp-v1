package com.fintechapp.utils;

import java.util.Random;

public class GenerateRandomNumbers {
	String systemOTP, userOTP;
	static String s = "";
	static int ranNo;

	// Use getOTP( ) method to generate random OTP
	public static String getOTP(int len) {
		// Use for loop to iterate 4 times and generate random OTP
		for (int i = 0; i < len; i++) {
			// Generate random digit within 0-9
			ranNo = new Random().nextInt(9);
			s = s.concat(Integer.toString(ranNo));
		}
		return s;
	}

	
	   

	    public static String generate() {
	    	 final int ACCOUNT_ID_LENGTH = 10;
	        Random random = new Random();
	        StringBuilder sb = new StringBuilder();

	        for (int i = 0; i < ACCOUNT_ID_LENGTH; i++) {
	            int digit = random.nextInt(10);
	            sb.append(digit);
	        }

	        return sb.toString();
	    }
	
}
