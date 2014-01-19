package sy.service;

import java.util.List;

import sy.pageModel.Menu;

public interface MenuServiceI {

	public List<Menu> getTreeNode(String id);

	public List<Menu> getAllTreeNode();

}
