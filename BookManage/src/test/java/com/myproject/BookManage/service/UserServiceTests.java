package com.myproject.BookManage.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myproject.BookManage.entity.UserEntity;
import com.myproject.BookManage.service.ex.ServiceException;

/**
 * 用户类业务层接口的测试类
 * @author clc
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

	@Autowired 
	UserService service;
	
	@Test
	public void userReg () {
		try {
			UserEntity user = new UserEntity();
			user.setUsername("clc1212");
			user.setPassword("zxcv");
			user.setSalt("ffff");
			user.setGender(1);
			user.setEmail("clc1212@163.com");
			user.setPhone("13800138000");
			service.userReg(user);
			System.err.println("OK");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void userLogin () {
		try {
			String username = "clc1212";
			String password = "clc921208";
			UserEntity result = service.userLogin(username, password);
			System.err.println(result);
		} catch (Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
}
