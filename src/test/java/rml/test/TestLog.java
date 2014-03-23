package rml.test;

import org.apache.log4j.Logger;

import org.junit.Test;

public class TestLog {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestLog.class);

	@Test
	public void test() {

		logger.debug("log debug");
		logger.info("log info");
		logger.warn("log warn");
		logger.error("log error");
	}
}
