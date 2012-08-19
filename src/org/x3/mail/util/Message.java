package org.x3.mail.util;

public class Message {

	/**
	 * The format of the message. Will eventually have a getter and setter if I
	 * feel like adding them.
	 */
	private final String messageFormat = "<%s> %s";
	private final String sentFormat = "To %s: %s";
	private boolean cancel;
	private boolean unread;

	private String sender;
	private String recipient;
	private String message;
	private MailPriority priority;

	public Message(String sender, String recipient, String message,
			MailPriority priority) {
		this.sender = sender;
		this.recipient = recipient;
		this.message = message;
		this.priority = priority;
		cancel = false;
		unread = true;
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

	/**
	 * Returns the format of the message when it is sent
	 * 
	 * @return Sent format of the message
	 */

	public String getSentFormat() {
		return sentFormat;
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
	 * Returns whether the message has been cancelled. Modelled after event
	 * cancellations.
	 * 
	 * @return Cancelled
	 */
	public Boolean isCancelled() {
		return cancel;
	}

	/**
	 * Cancels or un-cancels a message. Modelled after event cancellations.
	 * 
	 * @param cancel
	 *            Whether to cancel the message
	 */
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

	public Boolean isUnread() {
		return unread;
	}

	public void setUnread(boolean unread) {
		this.unread = unread;
	}

	public String getPrefix() {
		switch (priority.getCode()) {
		case 5:
			return "REPORT: ";
		case 4:
			return "URGENT: ";
		default:
			return "";
		}
	}

}
