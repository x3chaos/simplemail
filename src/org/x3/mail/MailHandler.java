package org.x3.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.x3.mail.util.Mailbox;

public class MailHandler {

	File mailFile;

	public MailHandler(File file) {
		this.mailFile = file;
	}

	/**
	 * Loads the mail from the file specified in the constructor, unless
	 * setMailFile() has been used.
	 * 
	 * @return The entire mail object.
	 * @throws FileNotFoundException
	 *             if the file is not found.
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Mailbox> load() throws FileNotFoundException,
			IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				mailFile));
		HashMap<String, Mailbox> result = (HashMap<String, Mailbox>) in
				.readObject();
		in.close();
		return result;
	}

	/**
	 * Saves the mail object to the file specified in the constructor, unless
	 * setMailFile() has been used.
	 * 
	 * @param mail
	 *            The mail object
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void save(HashMap<String, Mailbox> mail)
			throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				mailFile));
		out.writeObject(mail);
		out.flush();
		out.close();
	}

	/**
	 * Getter for the current mail file used by the handler
	 * 
	 * @return The mail file
	 */
	public File getMailFile() {
		return mailFile;
	}

	/**
	 * Sets the mail file to a different file than specified in the constructor
	 * 
	 * @param newFile
	 *            The new file
	 */
	public void setMailFile(File newFile) {
		this.mailFile = newFile;
	}

}