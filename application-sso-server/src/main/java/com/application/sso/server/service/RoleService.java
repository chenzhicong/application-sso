package com.application.sso.server.service;

import java.util.List;

import com.application.sso.server.entity.RoleQueryParams;
import com.application.sso.server.model.Role;
import com.github.pagehelper.PageInfo;

public interface RoleService {
	
	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	PageInfo<Role> findByPage(RoleQueryParams roleQueryParams, Integer pageNo, Integer pageSize);
	
	Role get(String id);
	
	void update(Role role);
	
	void insert(Role role);
	
	void delete(String id);
	
}
