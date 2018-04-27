package com.yedam.app.board;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.app.user.UserService;
import com.yedam.app.user.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = "classpath:config/*-context.xml")
public class UserClient {
	@Autowired
	ApplicationContext context;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	UserService userService;
	
	UserVO param;
	
	@Before
	public void init() {
		param = new UserVO();
		param.setId("user");
		System.out.println("before 실행");
	}
	
	@Test
	public void getBoardServiceTest() {
		UserVO vo = userService.getUser(param);
		assertEquals("user", vo.getId());
	}
}
