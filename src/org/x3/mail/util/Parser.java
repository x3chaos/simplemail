package org.x3.mail.util;

import java.util.HashMap;
import java.util.logging.Logger;

import org.l3eta.StringUtils;

public class Parser {

	private String debugFormat = "Sender: %s Recipient: %s Priority: %s Message: \"%s\"";
	private HashMap<String, Object> invalid = new HashMap<String, Object>();

	private String sender;
	private String recipient;
	private String message;
	private int messageStart;
	private MailPriority priority;

	public Parser(String[] args) {
		sender = args[0];
		if (args[1].toLowerCase().matches("priority:(.*)")) {
			recipient = null;
		} else {
			recipient = args[1].toLowerCase();
		}
		messageStart = 2;
		if (args[2].toLowerCase().matches("priority:(.*)")) {
			if (args[2].toLowerCase().matches("priority:[0-4]")) {
				priority = MailPriority.getByCode(Integer.valueOf(args[2]
						.split(":")[1]));
			} else {
				priority = MailPriority.NORMAL;
				invalid.put("priority", args[2].split(":")[1]);
			}
			messageStart++;
		} else {
			priority = MailPriority.NORMAL;
		}
		message = StringUtils.complete(args, messageStart);
	}

	public String getSender() {
		return sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public String getMessage() {
		return message;
	}

	public MailPriority getPriority() {
		return priority;
	}

	public void debugInfo(Logger log) {
		log.info(String.format(debugFormat, sender, recipient, priority + "",
				message));
	}

	public boolean hasInvalid(String param) {
		return invalid.containsKey(param.toLowerCase());
	}

	public Object getInvalid(String param) {
		return invalid.get(param.toLowerCase());
	}

	public HashMap<String, Object> getInvalidParams() {
		return invalid;
	}

}
