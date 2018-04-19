package com.application.sso.server.dao;


import com.application.sso.server.entity.RoleQueryParams;
import com.application.sso.server.model.Role;
import com.tech.application.base.dao.mybatis.BaseDao;

public interface RoleDao extends BaseDao<Role, RoleQueryParams, String>{

}