package com.application.sso.server.dao;

import org.apache.ibatis.annotations.Param;

import com.application.sso.server.entity.UserQueryParams;
import com.application.sso.server.model.User;
import com.tech.application.base.dao.mybatis.BaseDao;

public interface UserDao extends BaseDao<User, UserQueryParams, String>{
	
	User getByAccountAndPassword(@Param("account") String account, @Param("password") String password);
}