package org.x3.mail.util;

public class Message {

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

	public String getSender() {
		return sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public String getMessage() {
		return message;
	}
	
	public String getFormat() {
		return messageFormat;
	}
	
	public MailPriority getPriority() {
		return priority;
	}

}
