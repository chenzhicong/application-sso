package test.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.application.sso.server.model.App;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring-context.xml")
public class RedisTest {
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@Test
	public void test01() {
		redisTemplate.opsForValue().set("test01_key", "test01_value");
		String value = (String)redisTemplate.opsForValue().get("test01_key");
		System.out.println(value);
	}
	
	@Test
	public void test02() {
		App app = new App();
		app.setCode("yuanfen");
		app.setName("缘分");
		redisTemplate.opsForValue().set("test02_key", app);
	}
	
	@Test
	public void test03() {
		Map map = new HashMap();
		map.put("code", "yuanfen");
		map.put("name", "缘分");
		redisTemplate.opsForValue().set("test03_key", map);
	}
}
