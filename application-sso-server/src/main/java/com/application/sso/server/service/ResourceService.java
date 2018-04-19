package com.application.sso.server.service;

import com.application.sso.server.entity.ResourceQueryParams;
import com.application.sso.server.model.Resource;
import com.github.pagehelper.PageInfo;

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
}
