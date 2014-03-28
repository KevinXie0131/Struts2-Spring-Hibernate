package rml.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.BaseDaoI;
import rml.model.Tuser;
import rml.pageModel.DataGrid;
import rml.pageModel.User;
import rml.service.UserServiceI;
import rml.util.Encrypt;

@Service("userService")
public class UserServiceImpl implements UserServiceI {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	private BaseDaoI<Tuser> userDao;

	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}

	@Override
	public User save(User user) {
		Tuser t = new Tuser();
		BeanUtils.copyProperties(user, t, new String[] { "pwd" });
		t.setId(UUID.randomUUID().toString());
		t.setCreatedatetime(new Date());
		t.setPwd(Encrypt.e(user.getPwd()));
		userDao.save(t);
		BeanUtils.copyProperties(t, user);
		return user;
	}

	@Override
	public User login(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pwd", Encrypt.e(user.getPwd()));
		params.put("name", user.getName());
		Tuser t = userDao.get("from Tuser t where t.name = :name and t.pwd = :pwd", params);
		//"from Tuser t where t.name = ? and t.pwd = ?" must follow the order of parameters
		if (t != null) {
			return user;
		}
		return null;
	}

	@Override
	public DataGrid datagrid(User user) {
		DataGrid dg = new DataGrid();
		String hql = "from Tuser t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(user, hql, params);
		String totalHql = "select count(*) " + hql;  //should provide 'total' to dataGrid
		hql = addOrder(user, hql);
		List<Tuser> l = userDao.find(hql, params, user.getPage(), user.getRows()); //find by pagination
		List<User> nl = new ArrayList<User>();
		changeModel(l, nl);
		dg.setTotal(userDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<Tuser> l, List<User> nl) {
		if (l != null && l.size() > 0) {
			for (Tuser t : l) {
				User u = new User();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}

	private String addOrder(User user, String hql) {
		if (user.getSort() != null) {
			hql += " order by " + user.getSort() + " " + user.getOrder();
		}
		return hql;
	}

	private String addWhere(User user, String hql, Map<String, Object> params) {
		if (user.getName() != null && !user.getName().trim().equals("")) {
			hql += " where t.name like :name";
			params.put("name", "%%" + user.getName().trim() + "%%"); //%% beter than %
			//select * from tab t where t.name like '%admin%';
		}
		return hql;
	}

	@Override
	public void remove(String ids) {
		// for (String id : ids.split(",")) {
		// Tuser u = userDao.get(Tuser.class, id);
		// if (u != null) {
		// userDao.delete(u);
		// }
		// }
		String[] nids = ids.split(",");
		String hql = "delete Tuser t where t.id in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		userDao.executeHql(hql);
		
	}

	@Override
	public User edit(User user) {
		
		Tuser t = userDao.get(Tuser.class, user.getId());
		BeanUtils.copyProperties(user, t, new String[] {"id", "pwd"});
		return user;
	}

}
