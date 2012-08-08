package org.x3.mail.util;

import java.util.HashMap;

public enum MailPriority {
	LOWEST(0), LOW(1), NORMAL(2), HIGH(3), URGENT(4), REPORT(5);

	private static final HashMap<Integer, MailPriority> codes = new HashMap<Integer, MailPriority>();
	private final int code;

	private MailPriority(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static MailPriority getByCode(int code) {
		return codes.get(code);
	}

	static {
		for (MailPriority p : values()) {
			codes.put(p.getCode(), p);
		}
	}

}