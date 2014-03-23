package rml.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.BaseDaoI;
import rml.model.Tuser;
import rml.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService{
	
	private static final Logger logger = Logger.getLogger(TestServiceImpl.class);

	private BaseDaoI<Tuser> userDao;

	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void test() {
		
		logger.error("spring test");
	}

	@Override
	public void save(Tuser t) {
		
		userDao.save(t);
	}

}
