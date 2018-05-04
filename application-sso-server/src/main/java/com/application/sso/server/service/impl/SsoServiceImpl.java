package com.application.sso.server.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.application.sso.server.dao.AppDao;
import com.application.sso.server.dao.ResourceDao;
import com.application.sso.server.dao.RoleDao;
import com.application.sso.server.dao.UserDao;
import com.application.sso.server.entity.JwtData;
import com.application.sso.server.model.App;
import com.application.sso.server.model.Role;
import com.application.sso.server.model.User;
import com.application.sso.server.service.AppService;
import com.application.sso.server.service.JwtService;
import com.application.sso.server.service.ResourceService;
import com.application.sso.server.service.RoleService;
import com.application.sso.server.service.SsoService;
import com.application.sso.server.service.UserService;
import com.tech.application.base.exception.ServiceException;
import com.tech.application.base.util.PasswordUtil;

@Service
public class SsoServiceImpl implements SsoService{
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AppService appService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public String login(String account, String password) {
		
		String passwordEncrypted = PasswordUtil.encrypt(password);
		User user = userService.getByAccountAndPassword(account, passwordEncrypted);
		if(user == null) {
			throw new ServiceException("用户帐号或密码不正确");
		}
		
		Role role = roleService.get(user.getRoleId());
		App app = appService.get(role.getAppId());
		
		JwtData jwtUser = new JwtData();
		jwtUser.setUserId(user.getId());
		jwtUser.setRoleId(user.getRoleId());
		jwtUser.setAppCode(app.getCode());
		
		String jwt = jwtService.generateJWT(jwtUser);
		
		String key = jwtService.getCacheKey(user.getId());
		
		redisTemplate.opsForValue().set(key, jwt, 1, TimeUnit.DAYS);
		
		return jwt;
		
	}

	@Override
	public void logout(String jwt) {
		
		JwtData jwtData = jwtService.parseJwt(jwt);
		String userId = jwtData.getUserId();
		
		String key = jwtService.getCacheKey(userId);
		redisTemplate.delete(key);	
	}

	@Override
	public void resetPassword(String userId, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePassword(String userId, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register(String phone, String password, String verificationCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(String jwt) {
		
		JwtData jwtData = jwtService.parseJwt(jwt);
		String userId = jwtData.getUserId();
		
		User user = userService.get(userId);
		
		return user;
		
	}

	@Override
	public String getTokenName() {
		return jwtService.getTokenName();
	}

}
