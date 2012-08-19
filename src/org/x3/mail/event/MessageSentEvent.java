package org.x3.mail.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.x3.mail.util.Message;

public class MessageSentEvent extends Event {

	private static HandlerList handlers = new HandlerList();
	private final Message message;
	boolean cancel;

	public MessageSentEvent(Message message) {
		this.message = message;
		this.cancel = false;
	}

	public Message getMessage() {
		return message;
	}
	
	public Boolean isCancelled() {
		return cancel;
	}
	
	public void setCancelled(Boolean cancel) {
		this.cancel = cancel;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
