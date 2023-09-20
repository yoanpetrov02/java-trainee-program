package com.sirma.javacourse.regex.emailvalidator;

import org.junit.Assert;
import org.junit.Test;

public class EmailValidatorTest {

	@Test
	public void testIsValidEmailValidInput() {
		Assert.assertTrue(
				EmailValidator.isValidEmail("test.t-s@sir-ma.com"));
		Assert.assertTrue(
				EmailValidator.isValidEmail("h1ello@sirma999.com"));
		Assert.assertTrue(
				EmailValidator.isValidEmail("test@sirma.com"));
		Assert.assertTrue(
				EmailValidator.isValidEmail("eee@s-sirma.com"));
		Assert.assertTrue(
				EmailValidator.isValidEmail("eee@cc.BRitish.museum"));
	}

	@Test
	public void testIsValidEmailInvalidInput() {
		Assert.assertFalse(
				EmailValidator.isValidEmail("asd"));
		Assert.assertFalse(
				EmailValidator.isValidEmail(""));
		Assert.assertFalse(
				EmailValidator.isValidEmail("asd.;@asd.c"));
		Assert.assertFalse(
				EmailValidator.isValidEmail("@gmail.com"));
		Assert.assertFalse(
				EmailValidator.isValidEmail("youtube.com"));
	}

	@Test
	public void testIsValidEmailNull() {
		Assert.assertFalse(
				EmailValidator.isValidEmail(null));
	}
}
