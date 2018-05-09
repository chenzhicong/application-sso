package com.tech.application.sso.service;

import com.github.pagehelper.PageInfo;
import com.tech.application.sso.entity.RoleQueryParams;
import com.tech.application.sso.model.Role;

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
