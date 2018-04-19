package com.application.sso.server.dao;

import com.application.sso.server.entity.ResourceQueryParams;
import com.application.sso.server.model.Resource;
import com.tech.application.base.dao.mybatis.BaseDao;

public interface ResourceDao extends BaseDao<Resource, ResourceQueryParams, String>  {

}