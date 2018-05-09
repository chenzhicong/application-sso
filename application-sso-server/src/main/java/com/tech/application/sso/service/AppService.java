package com.tech.application.sso.service;

import com.tech.application.sso.entity.AppQueryParams;
import com.tech.application.sso.model.App;
import com.tech.application.sso.model.Role;
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
