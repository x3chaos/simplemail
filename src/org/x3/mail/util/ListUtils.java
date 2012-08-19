package org.x3.mail.util;

public class ListUtils {

	public static String createList(String[] o) {
		String result = "";
		for (int i = 0; i < o.length; i++) {
			String s = (i == o.length - 1) ? "" : ", ";
			result += o[i] + s;
		}
		return result;
	}

}
