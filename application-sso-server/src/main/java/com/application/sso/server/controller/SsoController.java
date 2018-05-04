package com.application.sso.server.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.sso.server.model.User;
import com.application.sso.server.service.SsoService;
import com.tech.application.base.controller.BaseController;
import com.tech.application.base.model.Result;
import com.tech.application.base.util.IdUtil;
import com.tech.application.base.util.PasswordUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Api(value = "SsoController", tags = { "登录登出接口" })
@RestController
@RequestMapping("sso/")
@Slf4j
public class SsoController extends BaseController {
	
	@Autowired
	private SsoService ssoService;
	
	@ApiOperation(value = "登录", httpMethod = "POST")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Result<String> login(
			@ApiParam(value = "用户帐号或手机号", required = true) @RequestParam(required = true) String account,
			@ApiParam(value = "用户登录密码", required = true) @RequestParam(required = true) String password,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		String jwt = ssoService.login(account, password);
		this.addTokenInCookie(jwt, request, response);
		return super.buildSuccessResult(jwt);
	}
	
	@ApiOperation(value = "登出", httpMethod = "POST")
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public Result<Object> logout(
			@ApiParam(value = "token", required = true) @RequestParam(required = true) String jwt) {
		
		ssoService.logout(jwt);
		return super.buildSuccessResult(null);
	}
	
	private void addTokenInCookie(String token, HttpServletRequest request, HttpServletResponse response) {
		// Cookie添加token
		Cookie cookie = new Cookie(ssoService.getTokenName(), token);
		cookie.setPath("/");
		if ("https".equals(request.getScheme())) {
			cookie.setSecure(true);
		}
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}
}
