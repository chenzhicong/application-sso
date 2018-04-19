package com.application.sso.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.sso.server.dao.ResourceDao;
import com.application.sso.server.entity.ResourceQueryParams;
import com.application.sso.server.model.Resource;
import com.application.sso.server.service.ResourceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	
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

}