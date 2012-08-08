package org.x3.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.x3.mail.util.Message;
import org.x3.mail.util.Parser;

public class SMExecutor implements CommandExecutor {

	private final SimpleMail sm;
	private static HashMap<String, String> helpTopics = new HashMap<String, String>();

	public SMExecutor(SimpleMail sm) {
		this.sm = sm;
	}

	@Override
	public boolean onCommand(CommandSender cmdSender, Command command,
			String label, String[] args) {
		if (args.length == 0) {
			return false;
		}
		String sender = (cmdSender instanceof Player) ? ((Player) cmdSender)
				.getName() : "Console";
		if (args[0].equalsIgnoreCase("send")) {
			if (args.length < 3) {
				return false;
			}
			args[0] = sender;
			Parser parser = new Parser(args);
			parser.debugInfo(sm.getLogger());
			cmdSender.sendMessage("Sending message to " + parser.getRecipient()
					+ " with priority " + parser.getPriority());
			Message message = new Message(parser);
			sm.send(message);
			if (message.getRecipient().equalsIgnoreCase("console")) {
				sm.getLogger().info(ChatColor.GREEN + "New mail for console.");
			}
			return true;
		} else if (args[0].equalsIgnoreCase("help")) {
			String topic;
			if (args.length == 1) {
				topic = "help";
			} else {
				topic = args[1].toLowerCase();
			}
			cmdSender.sendMessage(getTopic(topic));
			return true;
		} else if (args[0].equalsIgnoreCase("get")) {
			if (args.length > 1) {
				return false;
			}
			if (!(sm.hasMail(sender))) {
				cmdSender.sendMessage(ChatColor.GRAY + "You have no new mail.");
				return true;
			}
			ArrayList<Message> playerMail = sm.getMail(sender);
			Message[] messages = reorderMessages(playerMail);
			cmdSender.sendMessage(ChatColor.GRAY
					+ String.format("----- Mail: %s -----", sender));
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				ChatColor color = ChatColor.GRAY;
				String prefix = "";
				if (message.getPriority().getCode() == 4) {
					color = ChatColor.RED;
					prefix = "URGENT: ";
				}
				cmdSender.sendMessage(color
						+ String.format(prefix + message.getFormat(),
								message.getSender(), message.getMessage()));
			}
			sm.removeMail(sender);
			return true;
		}
		return false;
	}

	private String getTopic(String topic) {
		topic = topic.toLowerCase();
		if (helpTopics.containsKey(topic)) {
			return helpTopics.get(topic);
		} else {
			return String.format("Help topic %s does not exist.", topic);
		}
	}

	private Message[] reorderMessages(ArrayList<Message> mail) {
		Message[] result = new Message[mail.size()];
		int count = 0;
		for (int i = 4; i >= 0; i--) {
			for (Iterator<Message> it = mail.iterator(); it.hasNext();) {
				Message m = it.next();
				if (m.getPriority().getCode() == i) {
					result[count] = m;
					it.remove();
					count++;
				}
			}
		}
		return result;
	}

	static {
		helpTopics.put("send", "/mail send <target> [priority:#] <message>");
		helpTopics.put("get", "/mail get");
		helpTopics.put("help", "Topics: send, get");
	}

}