package com.tech.application.sso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.tech.application.base.controller.BaseController;
import com.tech.application.base.model.Result;
import com.tech.application.base.util.IdUtil;
import com.tech.application.sso.entity.ResourceQueryParams;
import com.tech.application.sso.model.App;
import com.tech.application.sso.model.Resource;
import com.tech.application.sso.service.AppService;
import com.tech.application.sso.service.ResourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Api(value = "ResourceController", tags = { "资源接口" })
@RestController
@RequestMapping("resource/")
@Slf4j
public class ResourceController extends BaseController {
	
	@Autowired
	private AppService appService;
	
	@Autowired
	private ResourceService resourceService;
	
	@ApiOperation(value = "分页查询资源", httpMethod = "GET")
	@RequestMapping(value = "findByPage", method = RequestMethod.GET)
	public Result<PageInfo<Resource>> findByPage(
			@ApiParam(value = "应用编码", required = true) @RequestParam(required = true) String appCode,
			@ApiParam(value = "角色id", required = false) @RequestParam(required = false) String roleId,
			@ApiParam(value = "资源名称", required = false) @RequestParam(required = false) String name,
			@ApiParam(value = "资源类型", required = false) @RequestParam(required = false) Integer type,
			@ApiParam(value = "状态  0:禁用   1:正常 ", required = false) @RequestParam(required = false) Integer status,
			@ApiParam(value = "页码", required = true) @RequestParam(required = true, defaultValue = "1") Integer pageNo,
			@ApiParam(value = "每页记录数", required = true) @RequestParam(required = true, defaultValue = "10") Integer pageSize) {

		ResourceQueryParams resourceQueryParams = new ResourceQueryParams();
		resourceQueryParams.setAppCode(appCode);
		resourceQueryParams.setName(name);
		resourceQueryParams.setType(type);
		resourceQueryParams.setStatus(status);

		PageInfo<Resource> data = resourceService.findByPage(resourceQueryParams, pageNo, pageSize);

		return super.buildSuccessResult(data);
	}

	@ApiOperation(value = "查询资源详情", httpMethod = "GET")
	@RequestMapping(value = "getResource", method = RequestMethod.GET)
	public Result<Resource> getResource(
			@ApiParam(value = "资源权限id", required = true) @RequestParam(required = true) String resourceId) {

		Resource resource = resourceService.get(resourceId);
		return super.buildSuccessResult(resource);
	}
	
	@ApiOperation(value = "新增资源", httpMethod = "POST")
	@RequestMapping(value = "addResource", method = RequestMethod.POST)
	public Result<Resource> addResource(
			@ApiParam(value = "应用编码", required = true) @RequestParam(required = true) String appCode,
			@ApiParam(value = "父资源id", required = false) @RequestParam(required = false) String parentId,
			@ApiParam(value = "资源名称", required = true) @RequestParam(required = true) String name,
			@ApiParam(value = "资源描述", required = false) @RequestParam(required = false) String description,
			@ApiParam(value = "资源类型  1:菜单  2:按钮", required = true) @RequestParam(required = true) Integer resourceType,
			@ApiParam(value = "资源url", required = false) @RequestParam(required = false) String url,
			@ApiParam(value = "状态 0:禁用 1:正常", required = false) @RequestParam(required = false, defaultValue = "1") Integer status) {
		
		App app = appService.getByCode(appCode);

		Resource resource = new Resource();
		resource.setId(IdUtil.generateUUID());
		resource.setAppId(app.getId());
		resource.setParentId(parentId);
		resource.setName(name);
		resource.setDescription(description);
		resource.setResourceType(resourceType);
		resource.setUrl(url);
		resource.setStatus(status);
		resourceService.insert(resource);
		return super.buildSuccessResult(null);
	}
	
	@ApiOperation(value = "更新角色权限", httpMethod = "POST")
	@RequestMapping(value = "updateRolePermission", method = RequestMethod.POST)
	public Result<Resource> updateRolePermission(
			@ApiParam(value = "角色id", required = true) @RequestParam(required = true) String roleId,
			@ApiParam(value = "资源id：可接收多个，用\",\"隔开 ", required = false) @RequestParam(required = false) String resourceIds) {
		
		List<String> resourceIdList = super.getParamList(resourceIds, ",");
		resourceService.updateRolePermission(roleId, resourceIdList);
		return super.buildSuccessResult(null);
	}
}
