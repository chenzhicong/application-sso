package com.tech.application.sso.dao;


import com.tech.application.sso.model.RolePermission;
import com.tech.application.base.dao.mybatis.BaseDao;

public interface RolePermissionDao extends BaseDao<RolePermission, RolePermission, String>{
	
	void deleteByRoleId(String roleId);

}