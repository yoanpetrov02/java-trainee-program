package com.sirma.javacourse.collections.exceptionsmanager;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionMessageManagerRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMessageManagerRunner.class);

	public static void main(String[] args) {
		ExceptionMessageManager messageManager = new ExceptionMessageManager();
		messageManager.addStringMessage("Invalid card number");
		messageManager.addStringMessage("Wrong PID");
		try {
			messageManager.addStringMessage("invalid message");
		} catch (IllegalArgumentException e) {
			LOGGER.error("Error while adding message");
		}

		LOGGER.info(messageManager.getMessage());
		LOGGER.info(Arrays.toString(
				ExceptionMessageManager.getMessages(messageManager.getMessage()).toArray()));
	}
}
