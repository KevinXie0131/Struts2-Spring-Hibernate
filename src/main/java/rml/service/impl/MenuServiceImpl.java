package rml.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.BaseDaoI;
import rml.model.Tmenu;
import rml.pageModel.Menu;
import rml.service.MenuServiceI;

@Service("menuService")
public class MenuServiceImpl implements MenuServiceI {

	private static final Logger logger = Logger.getLogger(MenuServiceImpl.class);

	private BaseDaoI<Tmenu> menuDao;

	public BaseDaoI<Tmenu> getMenuDao() {
		return menuDao;
	}

	@Autowired
	public void setMenuDao(BaseDaoI<Tmenu> menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public List<Menu> getTreeNode(String id) {
		List<Menu> nl = new ArrayList<Menu>();
		String hql = null;
		Map<String, Object> params = new HashMap<String, Object>();
		if (id == null || id.equals("")) {
			// get all root node
			hql = "from Tmenu t where t.tmenu is null";
		} else {
			// get all children node
			hql = "from Tmenu t where t.tmenu.id = :id ";
			params.put("id", id);
		}
		List<Tmenu> l = menuDao.find(hql, params);
		if (l != null && l.size() > 0) {
			for (Tmenu t : l) {
				Menu m = new Menu();
				BeanUtils.copyProperties(t, m);
				Set<Tmenu> set = t.getTmenus();
				if (set != null && !set.isEmpty()) {
					m.setState("closed");// show as folder
				} else {
					m.setState("open");// show as file
				}
				nl.add(m);
			}
		}
		return nl;
	}

	@Override
	public List<Menu> getAllTreeNode() {
		List<Menu> nl = new ArrayList<Menu>();
		String hql = "from Tmenu t";
		List<Tmenu> l = menuDao.find(hql);
		if (l != null && l.size() > 0) {
			for (Tmenu t : l) {
				Menu m = new Menu();
				BeanUtils.copyProperties(t, m);
				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("url", t.getUrl());
				m.setAttributes(attributes);
				Tmenu tm = t.getTmenu();// get the parent node of the current node
				if (tm != null) {
					m.setPid(tm.getId());
				}
				nl.add(m);
			}
		}
		return nl;
	}

}
