package com.tech.application.sso.dao;


import com.tech.application.sso.entity.RoleQueryParams;
import com.tech.application.sso.model.Role;
import com.tech.application.base.dao.mybatis.BaseDao;

public interface RoleDao extends BaseDao<Role, RoleQueryParams, String>{

}