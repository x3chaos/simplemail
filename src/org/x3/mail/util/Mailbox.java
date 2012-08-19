package org.x3.mail.util;

import java.util.ArrayList;
import java.util.HashMap;

public class Mailbox {

	private String owner = "";
	private final HashMap<BoxType, ArrayList<Message>> boxes = new HashMap<BoxType, ArrayList<Message>>();
	private ArrayList<Message> unread = new ArrayList<Message>();
	private ArrayList<Message> read = new ArrayList<Message>();
	private ArrayList<Message> urgent = new ArrayList<Message>();

	{
		boxes.put(BoxType.UNREAD, unread);
		boxes.put(BoxType.READ, read);
		boxes.put(BoxType.URGENT, urgent);
	}

	public Mailbox(String owner) {
		this.owner = owner;
	}

	private void add(Message message, BoxType newBox) {
		for (ArrayList<Message> m : boxes.values()) {
			if (m.contains(message)) {
				m.remove(message);
			}
		}
		ArrayList<Message> box = boxes.get(newBox);
		box.add(message);
	}

	public void addUnread(Message message) {
		add(message, BoxType.UNREAD);
	}

	public void addRead(Message message) {
		add(message, BoxType.READ);
	}

	public void addUrgent(Message message) {
		add(message, BoxType.URGENT);
	}

	public Boolean hasUnread() {
		return (unread.size() > 0);
	}

	public ArrayList<Message> get(BoxType box) {
		return boxes.get(box);
	}

	public ArrayList<Message> getUnread() {
		return get(BoxType.UNREAD);
	}

	public ArrayList<Message> getRead() {
		return get(BoxType.READ);
	}

	public ArrayList<Message> getUrgent() {
		return get(BoxType.URGENT);
	}

	public String getOwner() {
		return owner;
	}

	public enum BoxType {
		UNREAD("unread"), READ("read"), URGENT("urgent");

		private static final HashMap<String, BoxType> types = new HashMap<String, BoxType>();
		private final String type;

		private BoxType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public static BoxType getFromType(String type) {
			return types.get(type);
		}

		static {
			for (BoxType t : values()) {
				types.put(t.getType(), t);
			}
		}

	}

}
