package com.application.sso.server.service;

import com.application.sso.server.entity.AppQueryParams;
import com.application.sso.server.model.App;
import com.application.sso.server.model.Role;
import com.github.pagehelper.PageInfo;
import com.tech.application.base.service.BaseService;

public interface AppService {
	
	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	PageInfo<App> findByPage(AppQueryParams appQueryDTO, Integer pageNo, Integer pageSize);
	
	App get(String id);
	
	void update(App app);
	
	void insert(App app);
	
	void delete(String id);
	
	App getByCode(String code);
}
