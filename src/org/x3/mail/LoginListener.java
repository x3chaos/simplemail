package org.x3.mail;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.x3.mail.util.Mailbox;

public class LoginListener implements Listener {

	private SimpleMail sm;

	public LoginListener(SimpleMail sm) {
		this.sm = sm;
	}

	// TODO this
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		if (!sm.getMail().containsKey(player.getName().toLowerCase())) {
			sm.addMailbox(player.getName());
		} else {
			Mailbox mailbox = sm.getMailbox(player.getName());
			if (mailbox.hasUnread()) {
				player.sendMessage(ChatColor.GRAY
						+ String.format("You have %s unread messages.",
								mailbox.getUnread().size()));
			}
		}
	}
}
