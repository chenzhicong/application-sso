package com.tech.application.sso.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.tech.application.sso.entity.ResourceQueryParams;
import com.tech.application.sso.model.Resource;

public interface ResourceService {
	
	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	PageInfo<Resource> findByPage(ResourceQueryParams resourceQueryDTO, Integer pageNo, Integer pageSize);
	
	Resource get(String id);
	
	void update(Resource resource);
	
	void insert(Resource resource);
	
	void delete(String id);
	
	void updateRolePermission(String roleId, List<String> resourceIds);
}
