package com.sirma.javacourse.designpatterns.fluentinterface;

/**
 * Demonstrates the fluent mail implementation.
 */
public class MailRunner {

	public static void main(String[] args) {
		Mail mail = new MailBuilder("yoanpetrov0230@gmail.com")
				.to("example@asd.com")
				.subject("Test mail")
				.content("Test content for the fluent mail demonstration")
				.cc("ccexample@asd.com")
				.build();

		System.out.println(mail);
	}
}
