package org.x3.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.x3.mail.util.Message;

public class MailHandler {

	File mailFile;

	public MailHandler(File file) {
		this.mailFile = file;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, ArrayList<Message>> load()
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				mailFile));
		HashMap<String, ArrayList<Message>> result = (HashMap<String, ArrayList<Message>>) in
				.readObject();
		in.close();
		return result;
	}

	public void save(HashMap<String, ArrayList<Message>> mail)
			throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				mailFile));
		out.writeObject(mail);
		out.flush();
		out.close();
	}

	public File getMailFile() {
		return mailFile;
	}

}