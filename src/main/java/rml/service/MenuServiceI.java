package rml.service;

import java.util.List;

import rml.pageModel.Menu;

public interface MenuServiceI {

	public List<Menu> getTreeNode(String id);
	
	public List<Menu> getAllTreeNode();

}
