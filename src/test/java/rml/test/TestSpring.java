package rml.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import rml.service.UserServiceI;

public class TestSpring {
	
	private static final Logger logger = Logger.getLogger(TestSpring.class);
	
	@Test
	public void test() {
	//	ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring-core.xml", "classpath:spring-hibernate.xml" });

	//	UserServiceI userService = (UserServiceI) ac.getBean("userService");
	//	logger.info(userService);
	//	userService.test();
	
	//	TestService testService = (TestService) ac.getBean("testService");		
	//	testService.test();
		
		logger.info("done");
	}

}
