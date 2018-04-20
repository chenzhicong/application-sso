package com.application.sso.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.sso.server.dao.UserDao;
import com.application.sso.server.entity.UserQueryParams;
import com.application.sso.server.model.User;
import com.application.sso.server.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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

	public void update(User user) {
		userDao.update(user);
	}

	public void insert(User user) {
		userDao.insert(user);
	}

	public void delete(String id) {
		userDao.delete(id);
	}

	@Override
	public User get(String id) {
		return userDao.get(id);
	}

}
