package com.application.sso.server.service.impl;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.application.sso.server.entity.JwtData;
import com.application.sso.server.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {
	
	@Value("${jwt.secretKey}")
	private String jwtSecretKey;
	
	private String cacheKeyPrefix = "jwt_";
	
	private String tokenName = "Security-Token";
	
	private String claimUser = "user";
	
	@Override
	public String generateJWT(JwtData jwtData) {

		// 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		// 生成JWT的时间
		Date now = new Date();

		// 创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
		Claims claims = Jwts.claims();

		String userStr = JSON.toJSONString(jwtData);
		claims.put("user", userStr);

		claims.setIssuedAt(now);
		claims.setSubject("application-sso");

		// 下面就是在为payload添加各种标准声明和私有声明了
		JwtBuilder builder = Jwts.builder() // 这里其实就是new一个JwtBuilder，设置jwt的body
				.setClaims(claims) // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
				.signWith(signatureAlgorithm, jwtSecretKey); // 设置签名使用的签名算法和签名使用的秘钥

		return builder.compact();
	}
	
	@Override
	public JwtData parseJwt(String jwt) {
		
		Claims claims = Jwts.parser() // 得到DefaultJwtParser
				.setSigningKey(jwtSecretKey) // 设置签名的秘钥
				.parseClaimsJws(jwt).getBody();// 设置需要解析的jwt
		
		JwtData jwtData = JSON.parseObject((String)claims.get(claimUser), JwtData.class);
		
		return jwtData;
	}
	
	@Override
	public String getCacheKey(String userId) {
		return cacheKeyPrefix + userId;
	}
	
	@Override
	public String getJwt(HttpServletRequest request) {
		
		String token = null;
		
		token = request.getParameter(tokenName);
        if(StringUtils.isNotBlank(token)){
            return token; 
        }
        
        token = request.getHeader(tokenName);
        if(StringUtils.isNotBlank(token)){
            return token; 
        }
        
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(tokenName)){
                	token = cookie.getValue();
                	break;
                }
            }
        }
        return token;
	}

	@Override
	public String getTokenName() {
		return this.tokenName;
	}

	
}
