package org.x3.mail.util;

public class Message {

	/**
	 * The format of the message. Will eventually have a getter and setter if I
	 * feel like adding them.
	 */
	private final String messageFormat = "<%s> %s";

	private String sender;
	private String recipient;
	private String message;
	MailPriority priority;

	public Message(String sender, String recipient, String message,
			MailPriority priority) {
		this.sender = sender;
		this.recipient = recipient;
		this.message = message;
		this.priority = priority;
	}

	public Message(Parser parser) {
		this(parser.getSender(), parser.getRecipient(), parser.getMessage(),
				parser.getPriority());
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
	 * Returns the format of the message
	 * 
	 * @return The format of the message
	 */
	public String getFormat() {
		return messageFormat;
	}

	// TODO determine whether a setter is necessary

	/**
	 * Returns the priority of the message
	 * 
	 * @return The priority of the message
	 */
	public MailPriority getPriority() {
		return priority;
	}

}
