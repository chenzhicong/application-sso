package test.utils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tech.application.sso.service.SsoService;

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
