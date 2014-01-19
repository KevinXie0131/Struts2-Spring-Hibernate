package sy.service;

import sy.pageModel.User;

public interface UserServiceI {

	public void save(User user);

	public User login(User user);

}
