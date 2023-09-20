package com.sirma.javacourse.regex.ibanvalidator;

import org.junit.Assert;
import org.junit.Test;

public class IbanValidatorTest {

	@Test
	public void testValidateIbansValidString() {
		String input = "<bankAccounts>\n"
				+ "<iban>GR16 0110 1050 0000 1054 7023 795</iban>\n"
				+ "<iban>GB35 MIBG 4025 3432 1446 70</iban>\n"
				+ "<iban>SA80 8000 0375 6080 1019 0160</iban>\n"
				+ "<iban>CH51 0868 6001 2565 1500 1</iban>\n"
				+ "<iban>BG80 BNBG 9661 1020 3456 7840</iban>\n"
				+ "<iban>IL30 01BG 0300 0009 6339 234</iban>\n"
				+ "<iban>AL47 2121 1009 0000 0002 3569 8741</iban>\n"
				+ "<iban>AZ21 NABZ 0000 0000 1370 1000 1944</iban>\n"
				+ "<iban>BG80 BNBG 9661 1020 3456 7843</iban>\n"
				+ "</bankAccounts>";

		String expected = "<bankAccounts>\n"
				+ "<iban>GR16 0110 1050 0000 1054 7023 795</iban>\n"
				+ "<iban>GB35 MIBG 4025 3432 1446 70</iban>\n"
				+ "<iban>SA80 8000 0375 6080 1019 0160</iban>\n"
				+ "<iban>CH51 0868 6001 2565 1500 1</iban>\n"
				+ "<iban>****7840</iban>\n"
				+ "<iban>IL30 01BG 0300 0009 6339 234</iban>\n"
				+ "<iban>AL47 2121 1009 0000 0002 3569 8741</iban>\n"
				+ "<iban>AZ21 NABZ 0000 0000 1370 1000 1944</iban>\n"
				+ "<iban>****7843</iban>\n"
				+ "</bankAccounts>";

		Assert.assertEquals(expected, IbanValidator.validateIbans(input));
	}

	@Test
	public void testValidateIbansInvalidString() {
		String expected2 = "asd";
		String expected3 = "BG80 BNBG 9661 1020 3456 7843";

		Assert.assertEquals(expected2, IbanValidator.validateIbans("asd"));
		Assert.assertEquals(expected3, IbanValidator.validateIbans("BG80 BNBG 9661 1020 3456 7843"));
	}

	@Test
	public void testValidateIbansNull() {
		Assert.assertNull(IbanValidator.validateIbans(null));
	}
}
