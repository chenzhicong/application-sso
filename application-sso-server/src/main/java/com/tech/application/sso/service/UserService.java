package com.tech.application.sso.service;

import com.github.pagehelper.PageInfo;
import com.tech.application.sso.entity.UserQueryParams;
import com.tech.application.sso.model.User;

public interface UserService {
	
	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	PageInfo<User> findByPage(UserQueryParams userQueryParams, Integer pageNo, Integer pageSize);
	
	User get(String id);
	
	void update(User user);
	
	void insert(User user);
	
	void delete(String id);
	
	User getByAccountAndPassword(String account, String password);

}
