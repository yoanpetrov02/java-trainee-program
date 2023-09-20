package com.sirma.javacourse.io.transferobject;

import java.io.ByteArrayInputStream;
import org.junit.Assert;
import org.junit.Test;

public class ObjectTransfererTest {

	private static final String inputs = "123";

	@Test
	public void testTransferNoOffset() {
		ObjectTransferer transferer = new ObjectTransferer(
				new ByteArrayInputStream(inputs.getBytes()), System.out);

		Assert.assertEquals(2, transferer.transfer(2, 0));
	}

	@Test
	public void testTransferWithOffset() {
		ObjectTransferer transferer = new ObjectTransferer(
				new ByteArrayInputStream(inputs.getBytes()), System.out);

		Assert.assertEquals(2, transferer.transfer(3, 1));
	}

	@Test
	public void testTransferAll() {
		ObjectTransferer transferer = new ObjectTransferer(
				new ByteArrayInputStream(inputs.getBytes()), System.out);
		Assert.assertEquals(3, transferer.transferAll());
	}
}
