package com.tech.application.sso.service;

import javax.servlet.http.HttpServletRequest;

import com.tech.application.sso.entity.JwtData;

public interface JwtService {
	
	/**
	 * 生成jwt
	 * @param key
	 * @param jwtData
	 * @return
	 */
	String generateJWT(JwtData jwtData);
	
	/**
	 * 解析jwt
	 * @param key
	 * @param jwt
	 * @return
	 */
	JwtData parseJwt(String jwt);
	
	/**
	 * 获取缓存key
	 * @param userId
	 * @return
	 */
	String getCacheKey(String userId);
	
	/**
	 * 获取请求的token
	 * 1. 从请求参数获取jwt
	 * 2. 若1获取不到，从http header获取
	 * 3. 若2获取不到，从cookie获取
	 * 
	 * @param request http请求
	 * @return
	 */
	String getJwt(HttpServletRequest request);
	
	/**
	 * 获取jwt的名称
	 * @return
	 */
	String getTokenName();
	
	
	
}
