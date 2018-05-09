package com.tech.application.sso.dao;

import org.apache.ibatis.annotations.Param;

import com.tech.application.sso.entity.UserQueryParams;
import com.tech.application.sso.model.User;
import com.tech.application.base.dao.mybatis.BaseDao;

public interface UserDao extends BaseDao<User, UserQueryParams, String>{
	
	User getByAccountAndPassword(@Param("account") String account, @Param("password") String password);
}