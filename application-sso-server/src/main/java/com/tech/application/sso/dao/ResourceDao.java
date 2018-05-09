package com.tech.application.sso.dao;

import com.tech.application.sso.entity.ResourceQueryParams;
import com.tech.application.sso.model.Resource;
import com.tech.application.base.dao.mybatis.BaseDao;

public interface ResourceDao extends BaseDao<Resource, ResourceQueryParams, String>  {

}