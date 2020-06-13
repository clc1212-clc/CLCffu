package com.myproject.BookManage.controller;

import javax.servlet.http.HttpSession;

/**
 * 控制类的基类
 * @author clc
 *
 */
abstract class BaseController {

	/**
	 * 控制器响应结果代号:成功
	 */
	protected final static int OK = 2000;
	
	/**
	 * 从session中获取uid
	 * @param session HttpSession对象
	 * @return 当前登录用户的id
	 */
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	
	/**
	 * 从session中获取用户名
	 * @param session HttpSession对象
	 * @return 当前登录用户的用户名
	 */
	protected final String getUsernameFromSession(HttpSession session) {
		return session.getAttribute("username").toString();
	}
	
	protected final Integer getisManageFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("isManage").toString());
	}
	
}
