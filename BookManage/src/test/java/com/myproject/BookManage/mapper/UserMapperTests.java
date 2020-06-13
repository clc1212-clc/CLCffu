package com.myproject.BookManage.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myproject.BookManage.entity.BookEntity;
import com.myproject.BookManage.entity.UserEntity;

/**
 * 用户持久层接口的测试类
 * @author clc
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {

	@Autowired
	UserMapper mapper;
	
	@Test
	public void insert () {
		UserEntity user = new UserEntity();
		user.setUsername("clc");
		user.setPassword("asdf");
		user.setSalt("sadsad");
		user.setGender(1);
		user.setPhone("18335114564");
		user.setEmail("clc@163.com");
		user.setIsManager(0);
		Integer rows = mapper.insert(user);
		System.err.println(rows);
	}
	
	@Test
	public void updatePassword () {
		Integer uid = 1;
		String oldPassword = "zxcv";
		String newPassword = "asdf";
		Integer rows = mapper.updatePassword(uid, newPassword, oldPassword);
		System.err.println(rows);
	}
	
	@Test
	public void findByUsername () {
		String username = "clc1212";
		UserEntity result = mapper.findByUsername(username);
		System.err.println(result);
	}
	
	@Test
	public void updateUserInfo () {
		Integer uid = 1;
		UserEntity user = new UserEntity();
		user.setGender(1);
		user.setEmail("clc1212@163.com");
		user.setPhone("13800138000");
		Integer rows = mapper.updateUserInfo(uid, user);
		System.err.println(rows);
	}
	
	@Test
	public void findByUsernameAndPassword () {
		String username = "gw11111";
		String password = "39e87f1e4651ed2bf2c50c080ae51724";
		UserEntity result = mapper.findByUsernameAndPassword(username, password);
		System.err.println(result);
	}
	
}
