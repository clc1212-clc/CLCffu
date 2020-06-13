package com.myproject.BookManage.service.impl;

import java.util.List;
import java.util.UUID;

import javax.sound.midi.SysexMessage;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.myproject.BookManage.entity.UserEntity;
import com.myproject.BookManage.mapper.UserMapper;
import com.myproject.BookManage.service.UserService;
import com.myproject.BookManage.service.ex.InsertException;
import com.myproject.BookManage.service.ex.SelectException;
import com.myproject.BookManage.service.ex.UpdateException;
import com.myproject.BookManage.service.ex.UserAlreadyExistException;
import com.myproject.BookManage.service.ex.UserDefinedException;
import com.myproject.BookManage.service.ex.UserNotExistException;
import com.myproject.BookManage.service.ex.WrongPasswordException;

/**
 * 用户类接口的实现类
 * @author clc
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;
	
	@Override
	public void userReg(UserEntity user) {
		UserEntity result = mapper.findByUsername(user.getUsername());
		if (result != null) {
			throw new UserAlreadyExistException("注册失败!用户名已存在!");
		}
		String salt = UUID.randomUUID().toString();
		String password = user.getPassword();
		
		String md5password = getMd5Password(password, salt);
		user.setPassword(md5password);
		user.setSalt(salt);
		user.setIsManager(0);
		Integer rows = insert(user);
		if(rows != 1) {
			throw new InsertException("注册失败!插入数据时发生未知异常! ");
		}
	};
	
	@Override
	public UserEntity userLogin(String username, String password) {
		UserEntity result = findByUsername(username);	
		if(result == null) {
			throw new UserNotExistException("登录失败!用户名不存在!");
		}
		String pwd = getMd5Password(password, result.getSalt());
		System.err.println(pwd);
		System.err.println(result.getPassword());
		if(!pwd.equals(result.getPassword())) {
			throw new WrongPasswordException("登录失败!密码不正确!");
		}
		return result;
	}
	
	@Override
	public void changeUserInfo(Integer uid, UserEntity user) {
		Integer rows = updateUserInfo(uid, user);
		if(rows != 1 ) {
			throw new UpdateException("修改失败!修改时出现未知错误!");
		}
	}
	
	@Override
	public UserEntity showUserInfo(String username) {
		UserEntity result = findByUsername(username);
		if(result == null) {
			throw new UserNotExistException("查询失败!用户信息不存在!");
		}
		return result;
	}
	
	@Override
	public void setManager(String username, Integer isManager) {
		UserEntity result = findByUsername(username);
		if(result == null) {
			throw new UserNotExistException("修改失败!用户不存在!");
		}
		if(result.getIsManager() == 1) {
			throw new UserAlreadyExistException("修改失败!用户已经是管理员!");
		}
		if(isManager != 1) {
			throw new UserDefinedException("修改失败!当前用户权限不足!");
		}
		updateManageByUsername(username);
	}

	@Override
	public void deleteManager(Integer uid, Integer isManager) {
		UserEntity result = findByUid(uid);
		if(result == null) {
			throw new UserNotExistException("修改失败!用户不存在!");
		}
		if(result.getIsManager() == 0) {
			throw new UserAlreadyExistException("修改失败!用户已经不是管理员!");
		}
		if(isManager != 1) {
			throw new UserDefinedException("修改失败!当前用户权限不足!");
		}
		updateNoManageByUid(uid);
	}

	@Override
	public List<UserEntity> showAllManager() {
		List<UserEntity> result = findManager();
		if(result.size() < 0) {
			throw new SelectException("查询失败!出现位置错误!");
		}
		return result;
	}
	//-------------------------------------------------------------
	
	/**
	 * 通过用户名将用户改成管理员
	 * @param username
	 * @return 受影响的行数
	 */
	private Integer updateManageByUsername(String username) {
		return mapper.updateManageByUsername(username);
	};
	
	/**
	 * 通过用户id将用户取消管理员
	 * @param uid
	 * @return 受影响的行数
	 */
	private Integer updateNoManageByUid(Integer uid) {
		return mapper.updateNoManageByUid(uid);
	};
	
	/**
	 * 查询出所有管理员的信息
	 * @return 管理员信息的集合
	 */
	private List<UserEntity> findManager(){
		return mapper.findManager();
	};
	
	/**
	 * 通过uid更改用户的信息
	 * @param uid 用户id
	 * @param user 用户名
	 * @return 受影响的行数
	 */
	private Integer updateUserInfo(@Param("uid") Integer uid, 
						   @Param("user") UserEntity user) {
		return mapper.updateUserInfo(uid, user);
	};
	
	
	/**
	 * 通过用户名查找用户信息
	 * @param username 用户名
	 * @return 用户实体类
	 */
	private UserEntity findByUsername(String username) {
		return mapper.findByUsername(username);
	};
	
	/**
	 * 通过用户id查找用户信息
	 * @param uid 用户id
	 * @return 用户实体类
	 */
	private UserEntity findByUid(Integer uid) {
		return mapper.findByUid(uid);
	};
	
	/**
	 * 通过用户名和密码查询用户
	 * @param username 用户名
	 * @param password 密码
	 * @return 用户的实体类
	 */
	private UserEntity findByUsernameAndPassword(
			@Param("username") String username, 
			@Param("password") String password) {
		return mapper.findByUsernameAndPassword(username, password);
	};
	
	/**
	 * 将用户数据插入到数据表中
	 * @param user 用户数据的实体类
	 * @return 受影响的行数
	 */
	private Integer insert(UserEntity user) {
		return mapper.insert(user);
	}

	/**
	 * 执行密码加密
	 * 
	 * @param password 原始密码
	 * @param salt     盐值
	 * @return 加密后的密文
	 */
	private String getMd5Password(String password, String salt) {
		// 加密标准：
		// - 使用password + salt
		// - 循环加密3次
		System.err.println("\t准备执行加密……");
		System.err.println("\t原文：" + password);
		System.err.println("\t盐值：" + salt);
		String result = password + salt;
		for (int i = 0; i < 3; i++) {
			result = DigestUtils.md5DigestAsHex(result.getBytes());
		}
		System.err.println("\t密文：" + result);
		return result;
	}

	

	

	

	
}
