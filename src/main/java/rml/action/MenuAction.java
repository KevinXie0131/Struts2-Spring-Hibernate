package rml.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import rml.model.vo.Json;
import rml.model.vo.Menu;
import rml.service.MenuServiceI;
import rml.util.ExceptionUtil;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "menuAction", 
		results = { 
			@Result(name = "menu", location = "/demo/admin/menu.jsp"), 
		})
public class MenuAction extends BaseAction implements ModelDriven<Menu> {

	private static final Logger logger = Logger.getLogger(MenuAction.class);

	private Menu menu = new Menu();

	public Menu getModel() {
		return menu;
	}
	
	private MenuServiceI menuService;

	public MenuServiceI getMenuService() {
		return menuService;
	}

	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}
	
	public String menu() {
		return "menu";
	}

	/**
	 * get tree of home menu
	 */
	public void doNotNeedSession_tree() {
		super.writeJson(menuService.tree(menu, false));
	}

	public void doNotNeedSession_treeRecursive() {
		super.writeJson(menuService.tree(menu, true));
	}

	/**
	 * get treegrid of menu
	 */
	public void treegrid() {
		super.writeJson(menuService.treegrid(menu));
	}

}
