package com.sirma.javacourse.designpatterns.fluentinterface;

import java.io.File;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a Mail object. The class has a very bloated constructor, which leads to creating a MailBuilder class,
 * which handles the object creation for the Mail class.
 */
public class Mail {

	private static final Logger LOGGER = LoggerFactory.getLogger(Mail.class);

	private String from;
	private String to;
	private String subject;
	private String content;
	private String cc;
	private File[] attachments;

	public Mail(String from, String to, String subject, String content, String cc, File[] attachments) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.cc = cc;
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return String.format(
				"From: \"%s\"; To: \"%s\"; Subject: \"%s\""
						+ "; Content: \"%s\"; CC: \"%s\"; Attachments: \"%s\"",
		from, to, subject, content, cc, Arrays.toString(attachments));
	}
}
