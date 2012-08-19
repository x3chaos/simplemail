package org.x3.mail.permissions;

import org.x3.mail.SimpleMail;
import org.x3.mail.util.Message;

public class MailPermissions {

	private SimpleMail sm;

	public MailPermissions(SimpleMail sm) {
		this.sm = sm;
	}

	public boolean shouldCancel(Message message) {
		String sender = message.getSender();
		String recipient = message.getRecipient();
		if (sender.equalsIgnoreCase("Console")) {
			return false;
		}
		if (!sm.getPlayer(sender).hasPermission("simplemail.send.player")) {
			return true;
		}
		if (sm.isAdmin(recipient) && sm.adminsProtected()) {
			if (!sm.getPlayer(sender).hasPermission("simplemail.send.admin")) {
				return true;
			}
		}
		return false;
	}

}
