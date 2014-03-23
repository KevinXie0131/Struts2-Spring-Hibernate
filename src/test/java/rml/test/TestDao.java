package rml.test;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import rml.model.Tuser;
import rml.service.TestService;

public class TestDao {

	@Test
	public void test(){
/*		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml", "classpath:spring-hibernate.xml" });
		TestService testService = (TestService) ac.getBean("testService");
		System.out.println(testService);
		
		Tuser t = new Tuser();
		t.setId(UUID.randomUUID().toString());
		t.setCreatedatetime(new Date());
		t.setName("ABCDEF4");
		t.setPwd("123456");
		testService.save(t);
*/		
		System.out.println("done");
	}
}
