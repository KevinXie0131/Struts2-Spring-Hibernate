package rml.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import rml.model.vo.Json;
import rml.model.vo.RoleChart;
import rml.model.vo.SessionInfo;
import rml.model.vo.User;
import rml.service.UserServiceI;
import rml.util.ChartUtils;
import rml.util.ExceptionUtil;
import rml.util.IpUtil;
import rml.util.ResourceUtil;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "userAction", 
		results = { 
			@Result(name = "user", location = "/admin/user.jsp"), 
			@Result(name = "userAdd", location = "/admin/userAdd.jsp"), 
			@Result(name = "userEdit", location = "/admin/userEdit.jsp"), 
			@Result(name = "userRoleEdit", location = "/admin/userRoleEdit.jsp"),
			@Result(name = "chart", location = "/admin/chart.jsp")
		})
public class UserAction extends BaseAction implements ModelDriven<User> {

	private static final Logger logger = Logger.getLogger(UserAction.class);

	private User user = new User();

	public User getModel() {
		return user;
	}
	
	private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	public String user() {
		return "user";
	}

	public String userAdd() {
		return "userAdd";
	}

	public String userEdit() {
		return "userEdit";
	}

	public String userRoleEdit() {
		return "userRoleEdit";
	}
	
	public String chart() {
		List<RoleChart> datalist = userService.countRole();
		String filename = ChartUtils.getUserBarChart(datalist);
		request.setAttribute("filename", filename);
		return "chart";
	}

	public void doNotNeedSession_login() {
		Json j = new Json();
		User u = userService.login(user);
		if (u != null) {
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo.setUserId(u.getCid());
			sessionInfo.setLoginName(user.getCname());
			sessionInfo.setLoginPassword(user.getCpwd());
			sessionInfo.setIp(IpUtil.getIpAddr(ServletActionContext.getRequest()));
			ServletActionContext.getRequest().getSession().setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
			sessionInfo.setAuthIds(u.getAuthIds());
			sessionInfo.setAuthNames(u.getAuthNames());
			sessionInfo.setRoleIds(u.getRoleIds());
			sessionInfo.setRoleNames(u.getRoleNames());
			sessionInfo.setAuthUrls(u.getAuthUrls());

			j.setObj(sessionInfo);
			j.setSuccess(true);
			j.setMsg("Login successfully");
		} else {
			j.setMsg("Login unsuccessfully");
		}
		super.writeJson(j);
	}

	public void doNotNeedSession_reg() {
		Json j = new Json();
		//Test: can not add admin
		if(!("admin".equals(user.getCname()))){
			try {
				userService.save(user);
				j.setSuccess(true);
				j.setMsg("Register successfully");
			} catch (Exception e) {
				logger.error(ExceptionUtil.getExceptionMessage(e));
				j.setMsg("Register unsuccessfully");
			}
		}else{
			logger.error("Can not register admin");
			j.setMsg("Can not register admin");
		}
		super.writeJson(j);
	}

	public void doNotNeedSession_logout() {
		ServletActionContext.getRequest().getSession().invalidate();
		Json j = new Json();
		j.setSuccess(true);
		super.writeJson(j);
	}
	
	public void add() {
		Json j = new Json();
		//can not add admin
		if(!("admin".equals(user.getCname()))){
			try {			
				userService.save(user);
				j.setSuccess(true);
				j.setMsg("Add successfully");
			} catch (Exception e) {
				logger.error(ExceptionUtil.getExceptionMessage(e));
				j.setMsg("Add unsuccessfully");
			}
		}else{
			logger.error("Can not add admin");
			j.setMsg("Can not add admin");
		}
		super.writeJson(j);
	}

	public void edit() {
		Json j = new Json();
		//can not update admin
		if(!("0".equals(user.getCid()))){
			try {
				userService.update(user);
				j.setSuccess(true);
				j.setMsg("Edit successfully");
			} catch (Exception e) {
				logger.error(ExceptionUtil.getExceptionMessage(e));
				j.setMsg("Edit unsuccessfully");
			}
		}else{
			logger.error("Can not update admin");
			j.setMsg("Can not update admin");
		}
		super.writeJson(j);
	}
	
	public void delete() {
		Json j = new Json();
		//can not delete admin
		boolean isAdmin = false;
		String ids = user.getIds();
		if(ids!=null){
			for (String id : ids.split(",")){
				if (id.trim().equals("0")) {
					isAdmin = true;
				}
			}		
		}
		if(!isAdmin){
			try {
				userService.delete(user.getIds());
				j.setSuccess(true);
				j.setMsg("Delete successfully");
			}catch (Exception e) {
				logger.error(ExceptionUtil.getExceptionMessage(e));
				j.setMsg("Delete unsuccessfully");
			}
		}else{
			logger.error("Can not delete admin");
			j.setMsg("Can not delete admin");
		}	
		super.writeJson(j);
	}

	public void roleEdit() {
		Json j = new Json();
		//can not edit role admin
		boolean isAdmin = false;
		String ids = user.getIds();
		if(ids!=null){
			for (String id : ids.split(",")){
				if (id.trim().equals("0")) {
					isAdmin = true;
				}
			}		
		}
		if(!isAdmin){
			try {
				userService.roleEdit(user);
				j.setSuccess(true);
				j.setMsg("Edit role successfully");
			} catch (Exception e) {
				logger.error(ExceptionUtil.getExceptionMessage(e));
				j.setMsg("Edit role unsuccessfully");
			}
		}else{
			logger.error("Can not edit role admin");
			j.setMsg("Can not edit role admin");
		}
		super.writeJson(j);
	}

	public void datagrid() {
		super.writeJson(userService.datagrid(user));
	}

	public void doNotNeedSession_combobox() {
		super.writeJson(userService.combobox(user));
	}

	public void doNotNeedSession_datagrid() {
		if (user.getQ() != null && !user.getQ().trim().equals("")) {
			user.setCname(user.getQ());
		}
		super.writeJson(userService.datagrid(user));
	}

}
