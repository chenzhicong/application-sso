package com.application.sso.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.sso.server.dao.AppDao;
import com.application.sso.server.entity.AppQueryParams;
import com.application.sso.server.model.App;
import com.application.sso.server.service.AppService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppServiceImpl implements AppService {
	
	@Autowired
	private AppDao appDao;

	public PageInfo<App> findByPage(AppQueryParams appQueryParams, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		Page<App> page = appDao.findByPage(appQueryParams);
		PageInfo<App> pageInfo = new PageInfo<App>(page);
		return pageInfo;
	}

	@Override
	public App get(String id) {
		return appDao.get(id);
	}

	@Override
	public void update(App app) {
		appDao.update(app);
		
	}

	@Override
	public void insert(App app) {
		appDao.insert(app);
		
	}

	@Override
	public void delete(String id) {
		appDao.delete(id);
		
	}

	@Override
	public App getByCode(String code) {
		return appDao.getByCode(code);
	}
	

}
