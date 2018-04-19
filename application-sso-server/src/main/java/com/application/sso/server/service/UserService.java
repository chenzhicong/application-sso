package com.application.sso.server.service;

import com.application.sso.server.entity.UserQueryParams;
import com.application.sso.server.model.User;
import com.github.pagehelper.PageInfo;

public interface UserService {
	
	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	PageInfo<User> findByPage(UserQueryParams userQueryParams, Integer pageNo, Integer pageSize);
	
	void update(User user);
	
	void insert(User user);
	
	void delete(String id);
}
