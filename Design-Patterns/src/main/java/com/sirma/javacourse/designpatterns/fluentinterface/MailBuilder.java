package com.sirma.javacourse.designpatterns.fluentinterface;

import java.io.File;

/**
 * Builder class for Mail that implements the Builder design pattern. The class provides different steps for constructing
 * instances of the Mail class, some of which are necessary, while others are optional. The user can create a Mail
 * instance by creating a new MailBuilder, passing the necessary value for the "from" field in it, and then, optionally, call the
 * other methods such at to(), subject(), content() etc. to build the object step by step. This provides a lot of
 * readability, provided how bloated Mail's constructor is. The instance can be retrieved with the build() method, after
 * the construction is finished.
 */
public class MailBuilder {

	private String from;
	private String to;
	private String subject;
	private String content;
	private String cc;
	private File[] attachments;

	public MailBuilder(String from) {
		this.from = from;
		this.attachments = new File[] {};
	}

	public MailBuilder to(String to) {
		this.to = to;
		return this;
	}

	public MailBuilder subject(String subject) {
		this.subject = subject;
		return this;
	}

	public MailBuilder content(String content) {
		this.content = content;
		return this;
	}

	public MailBuilder cc(String cc) {
		this.cc = cc;
		return this;
	}

	public MailBuilder attachments(File[] attachments) {
		this.attachments = attachments;
		return this;
	}

	public Mail build() {
		return new Mail(from, to, subject, content, cc, attachments);
	}
}
