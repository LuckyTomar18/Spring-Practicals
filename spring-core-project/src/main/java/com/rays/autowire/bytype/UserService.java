package com.rays.autowire.bytype;

public class UserService {

	UserDAOInt userDao;

	public void setUserdao(UserDAOInt userDao) {
		this.userDao = userDao;
	}

	public void testAdd() {
		userDao.add();
	}

}
