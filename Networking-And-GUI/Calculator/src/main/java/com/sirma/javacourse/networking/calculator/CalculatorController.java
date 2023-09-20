package com.sirma.javacourse.networking.calculator;

import com.sirma.javacourse.designpatterns.calculator.CommandFactory;
import com.sirma.javacourse.networking.calculator.model.CalculatorArgument;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * Controller class for the application.
 */
public class CalculatorController {

	private static final CommandFactory COMMAND_FACTORY = new CommandFactory();

	private final CalculatorArgument argument1 = new CalculatorArgument();
	private final CalculatorArgument argument2 = new CalculatorArgument();
	private boolean appendToFirstArg = true;
	private String operation = null;

	@FXML
	private TextArea result;

	/**
	 * Processes a number/decimal point entered from the UI and appends it to the respective argument.
	 *
	 * @param e the {@code ActionEvent} to get the button text from.
	 */
	@FXML
	public void processNumber(ActionEvent e) {
		String buttonText = getButtonText(e);
		if (appendToFirstArg) {
			result.setText(argument1.append(buttonText).getString());
		} else {
			result.setText(argument2.append(buttonText).getString());
		}
	}

	/**
	 * Processes an operation entered from the UI.
	 *
	 * @param e the {@code ActionEvent} to get the button text from.
	 */
	@FXML
	public void processOperation(ActionEvent e) {
		if (appendToFirstArg) {
			operation = getButtonText(e);

			if ("-".equals(operation)) {
				if ("0".equals(argument1.getString())) {
					argument1.append("-");
					return;
				}
			}
			appendToFirstArg = false;
			result.setText("0");
		}
	}

	/**
	 * Calculates the result and updates the result {@code TextArea}.
	 */
	@FXML
	public void calculateResult() {
		if (operation == null) {
			return;
		}
		setResultText();
		resetArgs();
	}

	/**
	 * Deletes the last character of the current argument.
	 */
	@FXML
	public void deleteLastChar() {
		if (appendToFirstArg) {
			result.setText(argument1.deleteLastChar().getString());
		} else {
			result.setText(argument2.deleteLastChar().getString());
		}
	}

	/**
	 * Clears the calculator, resetting all arguments and the operation.
	 */
	@FXML
	public void clear() {
		result.setText("0");
		resetArgs();
	}

	/**
	 * Gets the text of the button that sent this {@code ActionEvent}.
	 *
	 * @param e the {@code ActionEvent}.
	 * @return the text of the button that sent the event.
	 */
	private String getButtonText(ActionEvent e) {
		return ((Button)e.getSource()).getText();
	}

	/**
	 * Sets the result {@code TextArea}'s text to the found result,
	 * or to an error message if the user tries to divide by zero.
	 */
	private void setResultText() {
		try {
			result.setText(getResultAsString());
		} catch (IllegalArgumentException e) {
			result.setText("Error - division by zero!");
		}
	}

	/**
	 * Formats the found result as a {@code String}.
	 *
	 * @return the formatted result.
	 */
	private String getResultAsString() {
		return String.format("%.2f",
				COMMAND_FACTORY.createCommand(operation).
						execute(argument1.getDouble(), argument2.getDouble()));
	}

	/**
	 * Resets the calculator's arguments and the operation, and switches back to appending to the first argument.
	 */
	private void resetArgs() {
		argument1.reset();
		argument2.reset();
		appendToFirstArg = true;
		operation = null;
	}
}
