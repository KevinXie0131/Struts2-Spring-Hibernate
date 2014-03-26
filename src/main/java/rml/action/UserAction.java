package rml.action;

import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.web.context.support.WebApplicationContextUtils;

import rml.model.Tuser;
import rml.pageModel.Json;
import rml.pageModel.User;
import rml.service.TestService;
import rml.service.UserServiceI;


import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "userAction")
public class UserAction extends BaseAction implements ModelDriven<User> {
	  
	private User user = new User();//must new(), let ModelDriven input the value

	@Override
	public User getModel() {
		return user;
	}

	private static final Logger logger = Logger.getLogger(UserAction.class);

	private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	public void reg() {
		Json j = new Json();
		try {
			userService.save(user);
			j.setSuccess(true);
			j.setMsg("register done");
		} catch (Exception e) {
			//j.setMsg(e.getMessage());
			j.setMsg("register fail");
		}

		super.writeJson(j);

	}

	public void add() {
		Json j = new Json();
		try {
			User u = userService.save(user);
			j.setSuccess(true);
			j.setMsg("add done");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);

	}

	public void login() {
		User u = userService.login(user);
		Json j = new Json();
		if (u != null) {
			j.setSuccess(true);
			j.setMsg("login successful");
		} else {
			j.setMsg("login fail, username or password wrong");
		}

		super.writeJson(j);
	}

	public void datagrid() {
		super.writeJson(userService.datagrid(user));
	}

	public void remove() {
		userService.remove(user.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("delete done");
		super.writeJson(j);
	}
	
	public void test(){
		logger.info("user action test");
		ServletContext application = ServletActionContext.getRequest().getSession().getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		TestService testService = (TestService) context.getBean("testService");
		System.out.println(testService);
		testService.test();
		
		Tuser t = new Tuser();
		t.setId(UUID.randomUUID().toString());
		t.setCreatedatetime(new Date());
		t.setName("abcdef");
		t.setPwd("123456");
		testService.save(t);
	}
}
