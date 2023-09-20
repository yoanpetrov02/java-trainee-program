package com.sirma.javacourse.networking.calculator.model;

public class CalculatorArgument {

	private static final String[] LEGAL_STRINGS =
			{ "-", ".", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	private StringBuilder argument;
	private boolean isDecimalNumber;
	private boolean isNegative;

	public CalculatorArgument() {
		argument = new StringBuilder();
		isDecimalNumber = false;
		isNegative = false;
	}

	public CalculatorArgument append(String arg) {
		parseInput(arg);
		return this;
	}

	public CalculatorArgument deleteLastChar() {
		if (argument.length() == 0) {
			return this;
		}
		int lastCharIndex = argument.length() - 1;
		if (argument.charAt(lastCharIndex) == '.') {
			isDecimalNumber = false;
		}
		argument.deleteCharAt(lastCharIndex);
		return this;
	}

	public String getString() {
		if ("".equals(argument.toString()) ||
				"-".equals(argument.toString())) {
			return "0";
		}
		if (argument.charAt(argument.length() - 1) == '.') {
			return argument.toString() + "0";
		}
		return argument.toString();
	}

	public double getDouble() {
		return Double.parseDouble(getString());
	}

	public void reset() {
		argument = new StringBuilder();
		isDecimalNumber = false;
		isNegative = false;
	}

	private void parseInput(String input) {
		if (!isLegalString(input)) {
			return;
		}
		if ("-".equals(input)) {
			appendMinus();
			return;
		}
		if (".".equals(input)) {
			appendDecimalPoint();
			return;
		}
		if ("0".equals(input)) {
			appendZero();
			return;
		}
		if (isZeroTheOnlyChar()) {
			argument.deleteCharAt(0);
		}
		argument.append(input);
	}

	private boolean isLegalString(String str) {
		for (String legalString : LEGAL_STRINGS) {
			if (legalString.equals(str)) {
				return true;
			}
		}
		return false;
	}

	private void appendMinus() {
		if (isNegative) {
			return;
		}
		if (argument.length() == 0) {
			argument.append("-");
			isNegative = true;
		}
	}

	private void appendDecimalPoint() {
		if (isDecimalNumber) {
			return;
		}
		if (argument.length() == 0) {
			argument.append("0.");
		} else {
			argument.append(".");
		}
		isDecimalNumber = true;
	}

	private void appendZero() {
		if (!isZeroTheOnlyChar()) {
			argument.append("0");
		}
	}

	private boolean isZeroTheOnlyChar() {
		return (argument.length() == 1 &&
				argument.charAt(0) == '0');
	}
}
