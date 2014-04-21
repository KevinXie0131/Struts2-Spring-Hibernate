package rml.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import rml.model.vo.Json;
import rml.model.vo.Role;
import rml.service.RoleServiceI;
import rml.util.ExceptionUtil;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "roleAction", 
		results = { 
			@Result(name = "role", location = "/admin/role.jsp"), 
			@Result(name = "roleAdd", location = "/admin/roleAdd.jsp"), 
			@Result(name = "roleEdit", location = "/admin/roleEdit.jsp") 
		})
public class RoleAction extends BaseAction implements ModelDriven<Role> {

	private static final Logger logger = Logger.getLogger(RoleAction.class);

	private Role role = new Role();
	
	public Role getModel() {
		return role;
	}
	
	private RoleServiceI roleService;

	public RoleServiceI getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(RoleServiceI roleService) {
		this.roleService = roleService;
	}

	/**
	 * goto role management page
	 */
	public String role() {
		return "role";
	}

	public String roleAdd() {
		return "roleAdd";
	}

	public String roleEdit() {
		return "roleEdit";
	}

	/**
	 * add role
	 */
	public void add() {
		Json j = new Json();
		try {
			roleService.add(role);
			j.setSuccess(true);
			j.setMsg("Add successfully");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("Add unsuccessfully");
		}
		super.writeJson(j);
	}

	/**
	 * edit role
	 */
	public void edit() {
		Json j = new Json();
		try {
			roleService.edit(role);
			j.setSuccess(true);
			j.setMsg("Edit successfully");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("Edit unsuccessfully");
		}
		super.writeJson(j);
	}

	/**
	 * delete roles
	 */
	public void delete() {
		Json j = new Json();
		try {
			roleService.delete(role.getIds());
			j.setSuccess(true);
			j.setMsg("Delete successfully");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("Delete unsuccessfully");
		}
		super.writeJson(j);
	}

	/**
	 * get datagrid of role
	 */
	public void datagrid() {
		super.writeJson(roleService.datagrid(role));
	}
	
	/**
	 * get combobox of role
	 */
	public void doNotNeedSession_combobox() {
		super.writeJson(roleService.combobox());
	}

}
