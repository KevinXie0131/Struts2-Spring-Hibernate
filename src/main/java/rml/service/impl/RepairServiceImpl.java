package rml.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.BaseDaoI;
import rml.model.Tmenu;
import rml.model.Tuser;
import rml.service.RepairServiceI;
import rml.util.Encrypt;

@Service("repairService")
public class RepairServiceImpl  implements RepairServiceI {

	private BaseDaoI<Tmenu> menuDao;
	
	private BaseDaoI<Tuser> userDao;
  
	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}

	public BaseDaoI<Tmenu> getMenuDao() {
		return menuDao;
	}

	@Autowired
	public void setMenuDao(BaseDaoI<Tmenu> menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	synchronized public void repair() {

		repairUser();
		repairMenu();

	}

	private void repairMenu() {
		
		System.out.println("repaire menu");
		
		Tmenu root = new Tmenu();
		root.setId("0");
		root.setText("Home");
		root.setUrl("");
		menuDao.saveOrUpdate(root);

		Tmenu xtgl = new Tmenu();
		xtgl.setId("xtgl");
		xtgl.setTmenu(root);
		xtgl.setText("System");
		xtgl.setUrl("");
		xtgl.setIconcls("icon-help");
		menuDao.saveOrUpdate(xtgl);

		Tmenu yhgl = new Tmenu();
		yhgl.setId("yhgl");
		yhgl.setTmenu(xtgl);
		yhgl.setText("User");
		yhgl.setUrl("/admin/yhgl.jsp");
		yhgl.setIconcls("icon-add");
		menuDao.saveOrUpdate(yhgl);

		Tmenu jsgl = new Tmenu();
		jsgl.setId("jsgl");
		jsgl.setTmenu(xtgl);
		jsgl.setText("Role");
		jsgl.setUrl("/admin/test1.jsp");
		menuDao.saveOrUpdate(jsgl);

		Tmenu qxgl = new Tmenu();
		qxgl.setId("qxgl");
		qxgl.setTmenu(xtgl);
		qxgl.setText("Access");
		qxgl.setUrl("/admin/test2.jsp");
		menuDao.saveOrUpdate(qxgl);

		Tmenu cdgl = new Tmenu();
		cdgl.setId("cdgl");
		cdgl.setTmenu(xtgl);
		cdgl.setText("Menu");
		cdgl.setUrl("/admin/test3.jsp");
		menuDao.saveOrUpdate(cdgl);

		Tmenu buggl = new Tmenu();
		buggl.setId("buggl");
		buggl.setTmenu(xtgl);
		buggl.setText("BUG");
		menuDao.saveOrUpdate(buggl);
		
		//more menu
		Tmenu nextHome = new Tmenu();
		nextHome.setId("nexthome");
		nextHome.setTmenu(root);
		nextHome.setText("Service");
		nextHome.setUrl("");
		menuDao.saveOrUpdate(nextHome);
		
		Tmenu nextservice1 = new Tmenu();
		nextservice1.setId("nextservice1");
		nextservice1.setTmenu(nextHome);
		nextservice1.setText("Connect");
		nextservice1.setUrl("/admin/test1.jsp");
		menuDao.saveOrUpdate(nextservice1);

		Tmenu nextservice2 = new Tmenu();
		nextservice2.setId("nextservice2");
		nextservice2.setTmenu(nextHome);
		nextservice2.setText("Disconnect");
		nextservice2.setUrl("/admin/test2.jsp");
		menuDao.saveOrUpdate(nextservice2);
		
		Tmenu nextservice3 = new Tmenu();
		nextservice3.setId("nextservice3");
		nextservice3.setTmenu(nextHome);
		nextservice3.setText("Test3");
		nextservice3.setUrl("/admin/test3.jsp");
		menuDao.saveOrUpdate(nextservice3);
	}

	private void repairUser() {
		
		System.out.println("repaire user");
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("name", "admin");
		Tuser t = userDao.get("from Tuser t where t.name = :name and t.id != '0'", m);
		if (t != null) {
			t.setName(UUID.randomUUID().toString());
		}

		Tuser admin = new Tuser();
		admin.setId("0");
		admin.setName("admin");
		admin.setPwd(Encrypt.e("admin"));
		admin.setModifydatetime(new Date());
		userDao.saveOrUpdate(admin);
	}


}
