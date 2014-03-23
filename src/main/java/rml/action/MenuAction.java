package rml.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import rml.pageModel.Menu;
import rml.service.MenuServiceI;

import com.opensymphony.xwork2.ModelDriven;

@Action(value = "menuAction")
public class MenuAction extends BaseAction implements ModelDriven<Menu> {

	private static final Logger logger = Logger.getLogger(MenuAction.class);

	private Menu menu = new Menu();

	private MenuServiceI menuService;

	public MenuServiceI getMenuService() {
		return menuService;
	}

	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}

	@Override
	public Menu getModel() {
		return menu;
	}

	/**
	 * get tree node asynchronous 
	 */
	public void getTreeNode() {
		super.writeJson(menuService.getTreeNode(menu.getId()));
	}

	public void getAllTreeNode() {
		super.writeJson(menuService.getAllTreeNode());
	}

}
