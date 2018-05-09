package com.tech.application.sso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tech.application.base.util.IdUtil;
import com.tech.application.sso.dao.ResourceDao;
import com.tech.application.sso.dao.RolePermissionDao;
import com.tech.application.sso.entity.ResourceQueryParams;
import com.tech.application.sso.model.Resource;
import com.tech.application.sso.model.RolePermission;
import com.tech.application.sso.service.ResourceService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;

	@Autowired
	private RolePermissionDao rolePermissionDao;
	
	@Override
	public PageInfo<Resource> findByPage(ResourceQueryParams resourceQueryParams, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		Page<Resource> page = resourceDao.findByPage(resourceQueryParams);
		PageInfo<Resource> pageInfo = new PageInfo<Resource>(page);
		return pageInfo;
	}
	
	@Override
	public void update(Resource resource) {
		resourceDao.update(resource);
	}
	
	@Override
	public void insert(Resource resource) {
		resourceDao.insert(resource);
	}
	
	@Override
	public void delete(String id) {
		resourceDao.delete(id);
	}

	@Override
	public Resource get(String id) {
		return resourceDao.get(id);
	}

	@Override
	public void updateRolePermission(String roleId, List<String> resourceIds) {
		rolePermissionDao.deleteByRoleId(roleId);
		
		if(resourceIds != null && resourceIds.size() > 0) {
			for(String resourceId: resourceIds) {
				RolePermission rolePermission = new RolePermission();
				rolePermission.setId(IdUtil.generateUUID());
				rolePermission.setRoleId(roleId);
				rolePermission.setResourceId(resourceId);
				rolePermissionDao.insert(rolePermission);
			}
		}
	}

}
