package com.application.sso.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.application.sso.server.entity.RoleQueryParams;
import com.application.sso.server.model.App;
import com.application.sso.server.model.Role;
import com.application.sso.server.service.AppService;
import com.application.sso.server.service.RoleService;
import com.application.sso.server.service.impl.RoleServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.tech.application.base.controller.BaseController;
import com.tech.application.base.model.Result;
import com.tech.application.base.util.IdUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Api(value = "RoleController", tags = { "角色接口" })
@RestController
@RequestMapping("role/")
@Slf4j
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AppService appService;

	@ApiOperation(value = "分页查询角色", httpMethod = "GET")
	@RequestMapping(value = "findByPage", method = RequestMethod.GET)
	public Result<PageInfo<Role>> findByPage(
			@ApiParam(value = "应用编码", required = false) @RequestParam(required = false) String appCode,
			@ApiParam(value = "角色名称", required = false) @RequestParam(required = false) String name,
			@ApiParam(value = "角色状态  0:禁用   1:正常 ", required = false) @RequestParam(required = false) Integer status,
			@ApiParam(value = "页码", required = true) @RequestParam(required = true, defaultValue = "1") Integer pageNo,
			@ApiParam(value = "每页记录数", required = true) @RequestParam(required = true, defaultValue = "10") Integer pageSize) {

		RoleQueryParams roleQueryParams = new RoleQueryParams();
		roleQueryParams.setAppCode(appCode);
		roleQueryParams.setName(name);
		roleQueryParams.setStatus(status);

		PageInfo<Role> data = roleService.findByPage(roleQueryParams, pageNo, pageSize);

		return super.buildSuccessResult(data);
	}

	@ApiOperation(value = "查询角色详情", httpMethod = "GET")
	@RequestMapping(value = "getRole", method = RequestMethod.GET)
	public Result<Role> getRole(
			@ApiParam(value = "角色id", required = true) @RequestParam(required = true) String roleId) {

		Role role = roleService.get(roleId);
		return super.buildSuccessResult(role);
	}
	
	@ApiOperation(value = "新增角色", httpMethod = "POST")
	@RequestMapping(value = "addRole", method = RequestMethod.POST)
	public Result<Object> addRole(
			@ApiParam(value = "应用编码", required = true) @RequestParam(required = true) String appCode,
			@ApiParam(value = "角色名称", required = true) @RequestParam(required = true) String name,
			@ApiParam(value = "角色描述", required = false) @RequestParam(required = false) String description,
			@ApiParam(value = "状态 0:禁用 1:正常", required = false) @RequestParam(required = false, defaultValue = "1") Integer status) {
		
		App app = appService.getByCode(appCode);

		Role role = new Role();
		role.setId(IdUtil.getUUID());
		role.setAppId(app.getId());
		role.setName(name);
		role.setDescription(description);
		role.setStatus(status);
		roleService.insert(role);
		return super.buildSuccessResult(null);
	}
	
	/*
	@ApiOperation(value = "删除角色", httpMethod = "POST")
	@RequestMapping(value = "deleteRole", method = RequestMethod.POST)
	public Result<Role> deleteRole(
			@ApiParam(value = "角色id", required = true) @RequestParam(required = true) String roleId) {
		
		roleService.delete(roleId);
		return super.buildSuccessResult(null);
	}
	*/
}
