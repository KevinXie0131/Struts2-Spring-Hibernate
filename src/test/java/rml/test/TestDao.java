package rml.test;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import rml.model.po.Tuser;
import rml.model.vo.User;
import rml.service.UserServiceI;

public class TestDao {

	private static final Logger logger = Logger.getLogger(TestDao.class);
	
	@Test
	public void test(){
/*		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring-core.xml", "classpath:spring-hibernate.xml" });
		UserServiceI userService = (UserServiceI) ac.getBean("userService");
		logger.info(userService);
		
		User t = new User();
		t.setCid(UUID.randomUUID().toString());
		t.setCcreatedatetime(new Date());
		t.setCname("ABCDEF");
		t.setCpwd("123456");
		userService.save(t);
*/		
		System.out.println("save done");
	}
}
