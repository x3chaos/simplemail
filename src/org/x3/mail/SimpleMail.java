package org.x3.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.x3.mail.util.Message;

public class SimpleMail extends JavaPlugin {

	HashMap<String, ArrayList<Message>> mail;
	MailHandler mailHandler;
	Logger log;

	@Override
	public void onEnable() {
		log = this.getLogger();
		mailHandler = new MailHandler(new File(getDataFolder(),
				"simplemail.map"));
		mail = new HashMap<String, ArrayList<Message>>();
		this.getCommand("mail").setExecutor(new SMExecutor(this));
		log.info("SimpleMail enabled.");
	}

	public void registerNewMail(String recipient, Message message) {

	}

	public Player getPlayer(String name) {
		return getServer().getPlayer(name);
	}

	public boolean hasMail(String player) {
		return mail.containsKey(player.toLowerCase());
	}

	public ArrayList<Message> getMail(String player) {
		return mail.get(player.toLowerCase());
	}

	public void removeMail(String player) {
		mail.remove(player.toLowerCase());
	}

	public boolean isOnline(String player) {
		for (Player p : getServer().getOnlinePlayers()) {
			if (p.getName().equalsIgnoreCase(player)) {
				return true;
			}
		}
		return false;
	}

	public void send(Message message) {
		String recipient = message.getRecipient().toLowerCase();
		ArrayList<Message> playerMail;
		if (hasMail(recipient)) {
			playerMail = mail.get(recipient);
			mail.remove(recipient);
		} else {
			playerMail = new ArrayList<Message>();
		}
		playerMail.add(message);
		mail.put(recipient, playerMail);
		if (isOnline(recipient)) {
			notifyPlayer(getPlayer(recipient));
		}
	}

	public void notifyPlayer(Player player) {
		player.sendMessage(ChatColor.GREEN
				+ String.format("You have %s new message(s).",
						getMail(player.getName()).size()));
	}

}
