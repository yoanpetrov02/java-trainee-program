package com.sirma.javacourse.designpatterns.calculator;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for implementations of the {@code Command} interface.
 */
public class CommandFactory {

	private static final Map<String, Command> commands = new HashMap<>();

	public CommandFactory() {
		commands.put("+", new AddCommand());
		commands.put("-", new SubtractCommand());
		commands.put("*", new MultiplyCommand());
		commands.put("/", new DivideCommand());
		commands.put("^", new PowerCommand());
	}

	/**
	 * Returns the type of {@code Command} that the given key corresponds to, or null if
	 * the key does not correspond to a command.
	 *
	 * @param commandType the type of the command.
	 * @return the {@code Command} implementation that the given key corresponds to, or null if
	 * the command type does not exist.
	 */
	public Command createCommand(String commandType) {
		return commands.get(commandType);
	}
}
