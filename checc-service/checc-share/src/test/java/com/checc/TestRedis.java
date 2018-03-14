package com.checc;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ng.bayue.service.RedisCacheService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-context-checcshare.xml" })
public class TestRedis {
	
	@Resource(name = "redisCacheService1")
	private RedisCacheService redisCacheService;
	
	@Test
	public void test(){
		redisCacheService.setRedisCache("test1", "test123Shar", 60);
		String str = (String) redisCacheService.getRedisCache("test1");
		System.out.println(str);
	}

}
