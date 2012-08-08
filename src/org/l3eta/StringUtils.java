package org.l3eta;

public class StringUtils {

	public static String[] slice(String[] o, int index) {
		if (index == o.length) {
			return null;
		}
		if (index == 0) {
			return o;
		}
		String[] result = new String[o.length - index];
		for (int i = index; i < o.length; i++) {
			result[i - index] = o[i];
		}
		return result;
	}

	public static String complete(String[] o) {
		String result = "";
		for (int i = 0; i < o.length; i++) {
			result += o[i] + " ";
		}
		return result.trim();
	}

	public static String complete(String[] o, int index) {
		return complete(slice(o, index));
	}

}
