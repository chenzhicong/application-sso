package com.tech.application.sso.dao;

import com.tech.application.sso.entity.AppQueryParams;
import com.tech.application.sso.model.App;
import com.tech.application.base.dao.mybatis.BaseDao;

public interface AppDao extends BaseDao<App, AppQueryParams, String> {
	
	App getByCode(String code);
}