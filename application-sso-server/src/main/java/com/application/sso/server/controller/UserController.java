package com.application.sso.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.application.sso.server.entity.UserQueryParams;
import com.application.sso.server.model.App;
import com.application.sso.server.model.User;
import com.application.sso.server.service.AppService;
import com.application.sso.server.service.UserService;
import com.application.sso.server.service.impl.UserServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.tech.application.base.controller.BaseController;
import com.tech.application.base.model.Result;
import com.tech.application.base.util.IdUtil;
import com.tech.application.base.util.PasswordUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Api(value = "UserController", tags = { "用户接口" })
@RestController
@RequestMapping("user/")
@Slf4j
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AppService appService;

	@ApiOperation(value = "分页查询用户", httpMethod = "GET")
	@RequestMapping(value = "findByPage", method = RequestMethod.GET)
	public Result<PageInfo<User>> findByPage(
			@ApiParam(value = "应用编码", required = true) @RequestParam(required = true) String appCode,
			@ApiParam(value = "用户帐号", required = false) @RequestParam(required = false) String account,
			@ApiParam(value = "用户手机号", required = false) @RequestParam(required = false) String phone,
			@ApiParam(value = "用户姓名", required = false) @RequestParam(required = false) String name,
			@ApiParam(value = "用户别名", required = false) @RequestParam(required = false) String nickName,
			@ApiParam(value = "性别 -1:未知 0:男  1:女", required = false) @RequestParam(required = false) Integer sex,
			@ApiParam(value = "用户状态  0:禁用   1:正常 ", required = false) @RequestParam(required = false) Integer status,
			@ApiParam(value = "页码", required = true) @RequestParam(required = true, defaultValue = "1") Integer pageNo,
			@ApiParam(value = "每页记录数", required = true) @RequestParam(required = true, defaultValue = "10") Integer pageSize) {

		UserQueryParams userQueryParams = new UserQueryParams();
		userQueryParams.setAppCode(appCode);
		userQueryParams.setAccount(account);
		userQueryParams.setPhone(phone);
		userQueryParams.setName(name);
		userQueryParams.setNickName(nickName);
		userQueryParams.setSex(sex);
		userQueryParams.setStatus(status);

		PageInfo<User> data = userService.findByPage(userQueryParams, pageNo, pageSize);

		return super.buildSuccessResult(data);
	}

	@ApiOperation(value = "查询用户详情", httpMethod = "GET")
	@RequestMapping(value = "getUser", method = RequestMethod.GET)
	public Result<User> getUser(
			@ApiParam(value = "用户id", required = true) @RequestParam(required = true) String userId) {

		User user = userService.get(userId);
		return super.buildSuccessResult(user);
	}
	
	@ApiOperation(value = "新增用户", httpMethod = "POST")
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	public Result<Object> addUser(
			@ApiParam(value = "角色id", required = true) @RequestParam(required = true) String roleId,
			@ApiParam(value = "用户帐号", required = true) @RequestParam(required = true) String account,
			@ApiParam(value = "用户手机号", required = true) @RequestParam(required = true) String phone,
			@ApiParam(value = "用户登录密码", required = true) @RequestParam(required = true) String password,
			@ApiParam(value = "用户姓名", required = false) @RequestParam(required = false) String name,
			@ApiParam(value = "用户别名", required = false) @RequestParam(required = false) String nickName,
			@ApiParam(value = "性别 -1:未知 0:男  1:女", required = false) @RequestParam(required = false, defaultValue="-1") Integer sex,
			@ApiParam(value = "用户状态  0:禁用   1:正常 ", required = false) @RequestParam(required = false, defaultValue="1") Integer status) {
		
		User user = new User();
		user.setId(IdUtil.generateUUID());
		user.setRoleId(roleId);
		user.setAccount(account);
		user.setPhone(phone);
		user.setPassword(PasswordUtil.encrypt(password));
		user.setName(name);
		user.setNickName(nickName);
		user.setSex(sex);
		user.setStatus(status);
		userService.insert(user);
		return super.buildSuccessResult(null);
	}
	
	/*
	@ApiOperation(value = "登录", httpMethod = "POST")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Result<Object> login(
			@ApiParam(value = "登录名或手机号", required = true) @RequestParam(required = true) String account,
			@ApiParam(value = "用户登录密码", required = true) @RequestParam(required = true) String password) {
		
		User user = new User();
		user.setId(IdUtil.generateUUID());
		user.setRoleId(roleId);
		user.setAccount(account);
		user.setPhone(phone);
		user.setPassword(PasswordUtil.encrypt(password));
		user.setName(name);
		user.setNickName(nickName);
		user.setSex(sex);
		user.setStatus(status);
		userService.insert(user);
		return super.buildSuccessResult(null);
	}
	*/
	
	/*
	@ApiOperation(value = "删除用户", httpMethod = "POST")
	@RequestMapping(value = "deleteUser", method = RequestMethod.POST)
	public Result<User> deleteUser(
			@ApiParam(value = "用户id", required = true) @RequestParam(required = true) String userId) {
		
		userService.delete(userId);
		return super.buildSuccessResult(null);
	}
	*/
}
