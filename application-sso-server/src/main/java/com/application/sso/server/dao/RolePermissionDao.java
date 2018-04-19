package com.application.sso.server.dao;


import com.application.sso.server.model.RolePermission;
import com.tech.application.base.dao.mybatis.BaseDao;

public interface RolePermissionDao extends BaseDao<RolePermission, RolePermission, String>{
	
	void batchInsert(RolePermission rolePermission);
	
	void deleteByRoleId(String roleId);

}