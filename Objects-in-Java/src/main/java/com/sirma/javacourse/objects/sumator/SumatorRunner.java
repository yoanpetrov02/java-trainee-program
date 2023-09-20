package com.sirma.javacourse.objects.sumator;

import java.math.BigInteger;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SumatorRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(SumatorRunner.class);

	public static void main(String[] args) {
		LOGGER.info("Sum of integer numbers: {} + {}", 123, 123);
		LOGGER.info(String.valueOf(Sumator.sum(123, 123)));

		LOGGER.info("Sum of long numbers: {} + {}", 123123123123L, 123123123123L);
		LOGGER.info(String.valueOf(Sumator.sum(123123123123L, 123123123123L)));

		LOGGER.info("Sum of float numbers: {} + {}", 123.123f, 123.123f);
		LOGGER.info(String.valueOf(Sumator.sum(123.123f, 123.123f)));

		LOGGER.info("Sum of double numbers: {} + {}", 123.123123123d, 123.123123123d);
		LOGGER.info(String.valueOf(Sumator.sum(123.123123123d, 123.123123123d)));

		LOGGER.info("Sum of BigInteger numbers: {} + {}",
				"123123123123123123123123123123", "123123123123123123123123123123");
		LOGGER.info(String.valueOf(
				Sumator.sum(
						new BigInteger("123123123123123123123123123123"),
						new BigInteger("123123123123123123123123123123"))));

		LOGGER.info("Sum of BigDecimal numbers: {} + {}",
				"123123123123123123.123123123123123123", "123123123123123123.123123123123123123");
		LOGGER.info(String.valueOf(
				Sumator.sum(
						new BigDecimal("123123123123123123.123123123123123123"),
						new BigDecimal("123123123123123123.123123123123123123"))));

		LOGGER.info("Sum of numbers, represented by Strings: {} + {}",
				"120129837891231212312", "120129837891231212312");
		LOGGER.info(Sumator.sum("120129837891231212312", "120129837891231212312"));
	}
}