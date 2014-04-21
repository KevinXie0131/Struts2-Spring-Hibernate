package rml.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import rml.model.vo.Auth;
import rml.model.vo.Json;
import rml.service.AuthServiceI;
import rml.util.ExceptionUtil;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "authAction", 
		results = { 
			@Result(name = "auth", location = "/admin/auth.jsp"), 
			@Result(name = "authAdd", location = "/admin/authAdd.jsp"), 
			@Result(name = "authEdit", location = "/admin/authEdit.jsp") 
		})
public class AuthAction extends BaseAction implements ModelDriven<Auth> {

	private static final Logger logger = Logger.getLogger(AuthAction.class);

	private Auth auth = new Auth();
	
	public Auth getModel() {
		return auth;
	}
	
	private AuthServiceI authService;

	public AuthServiceI getAuthService() {
		return authService;
	}

	@Autowired
	public void setAuthService(AuthServiceI authService) {
		this.authService = authService;
	}	

	public String auth() {
		return "auth";
	}

	public String authAdd() {
		return "authAdd";
	}

	public String authEdit() {
		return "authEdit";
	}

	/**
	 * get tree of auth
	 */
	public void doNotNeedSession_tree() {
		super.writeJson(authService.tree(auth, false));
	}

	public void doNotNeedSession_treeRecursive() {
		super.writeJson(authService.tree(auth, true));
	}

	/**
	 * get treegrid of auth
	 */
	public void treegrid() {
		super.writeJson(authService.treegrid(auth));
	}
	
	/**
	 * Add auth
	 */
	public void add() {
		Json j = new Json();
		try {
			authService.add(auth);
			j.setSuccess(true);
			j.setMsg("Add successfully");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("Add unsuccessfully");
		}
		super.writeJson(j);
	}

	/**
	 * Edit auth
	 */
	public void edit() {
		Json j = new Json();
		try {
			authService.edit(auth);
			j.setSuccess(true);
			j.setMsg("Edit successfully");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("Edit unsuccessfully");
		}
		super.writeJson(j);
	}

	/**
	 * Delete auth
	 */
	public void delete() {
		Json j = new Json();
		try {
			authService.delete(auth);
			j.setSuccess(true);
			j.setMsg("Delete successfully");
			j.setObj(auth.getCid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("Delete unsuccessfully");
		}
		super.writeJson(j);
	}

}
