package com.application.sso.server.dao;

import com.application.sso.server.entity.AppQueryParams;
import com.application.sso.server.model.App;
import com.tech.application.base.dao.mybatis.BaseDao;

public interface AppDao extends BaseDao<App, AppQueryParams, String> {
	
	App getByCode(String code);
}