package com.myproject.BookManage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.BookManage.entity.Check;
import com.myproject.BookManage.entity.UserEntity;
import com.myproject.BookManage.service.UserService;
import com.myproject.BookManage.util.JsonResult;

/**
 * 用户类的控制层
 * @author clc
 *
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController{

	@Autowired
	private UserService service;
	
	@RequestMapping("login")
	public JsonResult<Void> loginin(HttpSession session){
		session.setAttribute("uid", 1);
		return new JsonResult<Void>(OK);
	}
	
	// http://localhost:8080/users/reg?username=clc123,password=123
	@RequestMapping("reg")
	public JsonResult<Void> userReg(UserEntity user){
		service.userReg(user);
		return new JsonResult<Void>(OK);
	}
	
	@PostMapping("login")
	public JsonResult<UserEntity> userLogin(String username, String password, HttpSession session){
		UserEntity result = service.userLogin(username, password);
		session.setAttribute("uid", result.getUid());
		session.setAttribute("username", result.getUsername());
		session.setAttribute("isManage", result.getIsManager());
		System.err.println("!!!!!!!!" + result.getIsManager());
		return new JsonResult<UserEntity>(OK,result);
	}
	
	@GetMapping("check")
	public JsonResult<String> userCheck(HttpSession session){
		String username = getUsernameFromSession(session);
		System.err.println("!!!!!!!!" + username);
		return new JsonResult<String>(OK,username);
	}
	
	@GetMapping("showUserInfo")
	public JsonResult<UserEntity> showUserInfo(HttpSession session){
		String username = getUsernameFromSession(session);
		UserEntity user = service.showUserInfo(username);
		return new JsonResult<UserEntity>(OK,user);
	}
	
	@PostMapping("update")
	public JsonResult<Void> changeUserInfo(HttpSession session, UserEntity user){
		Integer uid = getUidFromSession(session);
		service.changeUserInfo(uid, user);
		return new JsonResult<Void>(OK);
	}
	
	@GetMapping("checkManage")
	public synchronized JsonResult<Check> manageCheck(HttpSession session){
		Check result = new Check();
		Integer isManage = Integer.valueOf(session.getAttribute("isManage").toString());
		String username = getUsernameFromSession(session);
		result.setIsManage(isManage);
		result.setUsername(username);
		return new JsonResult<Check>(OK,result);
	}
	
	@GetMapping("logout")
	public JsonResult<Void> userLogout(HttpSession session){
		session.removeAttribute("uid");
		session.removeAttribute("username");
		session.removeAttribute("isManage");
		return new JsonResult<Void>(OK);
	}
	
	@GetMapping("showUserInfoManage")
	public JsonResult<UserEntity> showUserInfoManage(HttpSession session, String username){
		System.err.println("username!!"+username);
		UserEntity user = service.showUserInfo(username);
		System.err.println("user!!"+user);
		return new JsonResult<UserEntity>(OK,user);
	}
	
	@PostMapping("setManager")
	public JsonResult<Void> setManager(String username, HttpSession session){
		Integer isManager = getisManageFromSession(session);
		service.setManager(username, isManager);
		return new JsonResult<Void>(OK);
	}
	
	@PostMapping("removeManager")
	public JsonResult<Void> deleteManager(Integer uid, HttpSession session){
		Integer isManager = getisManageFromSession(session);
		service.deleteManager(uid, isManager);
		return new JsonResult<Void>(OK);
	}
	
	@GetMapping("managers")
	public JsonResult<List<UserEntity>> showAllManagers(HttpSession session){
		List<UserEntity> result = service.showAllManager();
		return new JsonResult<List<UserEntity>>(OK,result);
	}
	
	
}
