package test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tech.application.sso.model.User;
import com.tech.application.sso.service.SsoService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring-context.xml")
@Slf4j
public class SsoServiceTest {
	
	@Autowired
	private SsoService ssoService;
	
	@Test
	public void test01() {
		
		String account = "chenzhicong";
		String password = "123456";
		String jwt = ssoService.login(account, password);
		
		User user = ssoService.getUser(jwt);
		
		System.out.println(user.getName());
		
	}
}
