package org.x3.mail.event;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.x3.mail.util.Message;

public class MessageReadEvent extends Event {

	private static HandlerList handlers = new HandlerList();
	private final CommandSender sender;
	private final ArrayList<Message> messages;

	public MessageReadEvent(CommandSender sender, ArrayList<Message> messages) {
		this.sender = sender;
		this.messages = messages;
	}

	public CommandSender getSender() {
		return sender;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
