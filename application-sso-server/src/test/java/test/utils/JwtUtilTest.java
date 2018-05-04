package test.utils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.sso.server.entity.JwtData;
import com.application.sso.server.service.SsoService;

import io.jsonwebtoken.Claims;

public class JwtUtilTest {
	
	String key = "test";
	
	@Autowired
	private SsoService ssoService;
	
	
	
	@Test
	public void testJWT() {
		String token = ssoService.login("chenzhicong", "123456");
		//JwtData jwtData = JwtUtil.parseJwt(JwtUtil., jwt)
	}
		
}
