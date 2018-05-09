package com.tech.application.sso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tech.application.sso.dao.UserDao;
import com.tech.application.sso.entity.UserQueryParams;
import com.tech.application.sso.model.User;
import com.tech.application.sso.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public PageInfo<User> findByPage(UserQueryParams userQueryParams, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		Page<User> page = userDao.findByPage(userQueryParams);
		PageInfo<User> pageInfo = new PageInfo<User>(page);
		return pageInfo;
	}
	
	@Override
	public void update(User user) {
		userDao.update(user);
	}
	
	@Override
	public void insert(User user) {
		userDao.insert(user);
	}
	
	@Override
	public void delete(String id) {
		userDao.delete(id);
	}

	@Override
	public User get(String id) {
		return userDao.get(id);
	}

	@Override
	public User getByAccountAndPassword(String account, String password) {
		return userDao.getByAccountAndPassword(account, password);
	}

}
