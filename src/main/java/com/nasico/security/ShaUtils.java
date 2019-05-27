package com.nasico.security;

import java.security.MessageDigest;

public class ShaUtils {
	private static String getFormattedText(byte[] bytes) {
		StringBuilder str = new StringBuilder();
		for (byte b : bytes) {
			String hv = Integer.toHexString(b & 0xFF);
			if (hv.length() < 2)
				str.append("0");
			str.append(hv);
		}
		return str.toString();
	}

	public static String encode(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
