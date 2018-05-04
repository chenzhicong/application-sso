package com.application.sso.server.service;

import org.apache.ibatis.annotations.Param;

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
	
	User get(String id);
	
	void update(User user);
	
	void insert(User user);
	
	void delete(String id);
	
	User getByAccountAndPassword(String account, String password);

}
