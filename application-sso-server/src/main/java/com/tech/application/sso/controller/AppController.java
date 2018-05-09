package com.tech.application.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.tech.application.base.controller.BaseController;
import com.tech.application.base.model.Result;
import com.tech.application.base.util.IdUtil;
import com.tech.application.sso.entity.AppQueryParams;
import com.tech.application.sso.model.App;
import com.tech.application.sso.service.AppService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Api(value = "AppController", tags = { "应用接口" })
@RestController
@RequestMapping("app/")
@Slf4j
public class AppController extends BaseController {

	@Autowired
	private AppService appService;

	@ApiOperation(value = "分页查询应用", httpMethod = "GET")
	@RequestMapping(value = "findByPage", method = RequestMethod.GET)
	public Result<PageInfo<App>> findByPage(
			@ApiParam(value = "应用编码", required = false) @RequestParam(required = false) String code,
			@ApiParam(value = "应用名称", required = false) @RequestParam(required = false) String name,
			@ApiParam(value = "角色状态  0:禁用   1:正常 ", required = false) @RequestParam(required = false) Integer status,
			@ApiParam(value = "页码", required = true) @RequestParam(required = true, defaultValue = "1") Integer pageNo,
			@ApiParam(value = "每页记录数", required = true) @RequestParam(required = true, defaultValue = "10") Integer pageSize) {
		
		log.info("test");
		AppQueryParams appQueryParams = new AppQueryParams();
		appQueryParams.setCode(code);
		appQueryParams.setName(name);
		appQueryParams.setStatus(status);

		PageInfo<App> data = appService.findByPage(appQueryParams, pageNo, pageSize);

		return super.buildSuccessResult(data);
	}

	@ApiOperation(value = "查询应用详情", httpMethod = "GET")
	@RequestMapping(value = "getApp", method = RequestMethod.GET)
	public Result<App> getApp(
			@ApiParam(value = "应用id", required = true) @RequestParam(required = true) String appId) {

		App app = appService.get(appId);
		return super.buildSuccessResult(app);
	}
	
	@ApiOperation(value = "新增应用", httpMethod = "POST")
	@RequestMapping(value = "addApp", method = RequestMethod.POST)
	public Result<Object> addApp(
			@ApiParam(value = "应用编码", required = true) @RequestParam(required = true) String appCode,
			@ApiParam(value = "应用名称", required = true) @RequestParam(required = true) String name,
			@ApiParam(value = "应用描述", required = false) @RequestParam(required = false) String description,
			@ApiParam(value = "状态 0:禁用 1:正常", required = false) @RequestParam(required = false, defaultValue = "1") Integer status) {
		
		App app = new App();
		app.setId(IdUtil.generateUUID());
		app.setCode(appCode);
		app.setName(name);
		app.setDescription(description);
		app.setStatus(status);
		appService.insert(app);
		return super.buildSuccessResult(null);
	}
	
	/*
	@ApiOperation(value = "删除应用", httpMethod = "POST")
	@RequestMapping(value = "deleteApp", method = RequestMethod.POST)
	public Result<Role> deleteApp(
			@ApiParam(value = "应用id", required = true) @RequestParam(required = true) String appId) {
		
		appService.delete(appId);
		return super.buildSuccessResult(null);
	}
	*/

}
