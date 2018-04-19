package com.application.sso.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.sso.server.dao.RoleDao;
import com.application.sso.server.entity.RoleQueryParams;
import com.application.sso.server.model.Role;
import com.application.sso.server.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	public PageInfo<Role> findByPage(RoleQueryParams roleQueryParams, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		Page<Role> page = roleDao.findByPage(roleQueryParams);
		PageInfo<Role> pageInfo = new PageInfo<Role>(page);
		return pageInfo;
	}
	
	public Role get(String id) {
		return roleDao.get(id);
	}

	public void update(Role role) {
		roleDao.update(role);
	}

	public void insert(Role role) {
		roleDao.insert(role);
	}

	public void delete(String id) {
		roleDao.delete(id);
	}

}
