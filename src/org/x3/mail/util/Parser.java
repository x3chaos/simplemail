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

	// TODO determine whether I want to clean this

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

	/**
	 * Returns the sender of the message
	 * 
	 * @return The name of the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * Returns the recipient of the message
	 * 
	 * @return The name of the recipient
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * Returns the text of the message
	 * 
	 * @return The text of the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Returns the priority of the message
	 * 
	 * @return The priority of the message
	 */
	public MailPriority getPriority() {
		return priority;
	}

	/**
	 * Logs the debug information to the given {@code Logger}. Used primarily as
	 * a debug option, but enabled because it's the only way Console has of
	 * keeping track of messages.. as of yet.
	 * 
	 * @param log
	 *            The {@code Logger} to which to log
	 */
	public void debugInfo(Logger log) {
		log.info(String.format(debugFormat, sender, recipient, priority + "",
				message));
	}

	/**
	 * Returns whether the player entered an invalid entry for the given
	 * parameter (e.g. "priority:fish")
	 * 
	 * @param param
	 *            The parameter to check
	 * @return True if the parameter was entered wrongly, else false
	 */
	public boolean hasInvalid(String param) {
		return invalid.containsKey(param.toLowerCase());
	}

	/**
	 * Returns the invalid entry for the given parameter (e.g. "fish" if
	 * "priority:fish" was passed)
	 * 
	 * @param param
	 *            The given parameter
	 * @return The invalid entry for the parameter
	 */
	public Object getInvalid(String param) {
		return invalid.get(param.toLowerCase());
	}

	/**
	 * Returns a {@code HashMap<String, Object>} of all of the invalid
	 * parameters and their entries.
	 * 
	 * @return The invalid parameters and their entries.
	 */
	public HashMap<String, Object> getInvalidParams() {
		return invalid;
	}

}
