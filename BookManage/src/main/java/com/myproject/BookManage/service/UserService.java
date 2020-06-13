package com.myproject.BookManage.service;

import java.util.List;

import com.myproject.BookManage.entity.UserEntity;

/**
 * 用户类的业务类
 * @author clc
 *
 */
public interface UserService {

	/**
	 * 用户注册
	 * @param user 用户实体类
	 * @return 
	 */
	void userReg(UserEntity user);
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 用户实体类
	 */
	UserEntity userLogin(String username, String password);
	
	/**
	 * 更改个人信息
	 * @param uid 用户id
	 * @param user 用户实体类
	 */
	void changeUserInfo(Integer uid, UserEntity user);
	
	/**
	 * 显示用户的信息
	 * @param username 用户名
	 * @return 用户实体类
	 */
	UserEntity showUserInfo(String username);
	
	
	
	/**
	 * 显示所有管理员
	 * @return
	 */
	List<UserEntity> showAllManager();

	/**
	 * 設置為管理員
	 * @param username
	 */
	void setManager(String username, Integer isManager);
	
	/**
	 * 取消管理员
	 * @param uid
	 */
	void deleteManager(Integer uid, Integer isManager);
	
}
