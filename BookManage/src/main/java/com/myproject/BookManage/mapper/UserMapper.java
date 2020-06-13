package com.myproject.BookManage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myproject.BookManage.entity.UserEntity;

/**
 * 处理用户数据的持久层接口
 * @author clc
 *
 */
public interface UserMapper {
	
	/**
	 * 将用户数据插入到数据表中
	 * @param user 用户数据的实体类
	 * @return 受影响的行数
	 */
	Integer insert(UserEntity user);

	/**
	 * 通过用户的id和新密码旧密码进行密码修改
	 * @param uid 用户id
	 * @param newPassword 新密码
	 * @param oldPassword 旧密码
	 * @return 受影响的行数
	 */
	Integer updatePassword(
			@Param("uid") Integer uid,
			@Param("newPassword") String newPassword,
			@Param("oldPassword") String oldPassword);
	
	/**
	 * 通过uid更改用户的信息
	 * @param uid 用户id
	 * @param user 用户名
	 * @return 受影响的行数
	 */
	Integer updateUserInfo(@Param("uid") Integer uid, 
						   @Param("user") UserEntity user);
	
	/**
	 * 通过用户名查找用户信息
	 * @param username 用户名
	 * @return 用户实体类
	 */
	UserEntity findByUsername(String username);
	
	/**
	 * 通过用户id查找用户信息
	 * @param uid 用户id
	 * @return 用户实体类
	 */
	UserEntity findByUid(Integer uid);

	/**
	 * 通过用户名和密码查询用户
	 * @param username 用户名
	 * @param password 密码
	 * @return 用户的实体类
	 */
	UserEntity findByUsernameAndPassword(
			@Param("username") String username, 
			@Param("password") String password);
	
	/**
	 * 通过用户名将用户改成管理员
	 * @param username
	 * @return 受影响的行数
	 */
	Integer updateManageByUsername(String username);
	
	/**
	 * 通过用户id将用户取消管理员
	 * @param uid
	 * @return 受影响的行数
	 */
	Integer updateNoManageByUid(Integer uid);
	
	/**
	 * 查询出所有管理员的信息
	 * @return 管理员信息的集合
	 */
	List<UserEntity> findManager();
	
	
}
