package com.myproject.BookManage.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 在借书 还书时检查是否登录的拦截器
 * @author clc
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession httpSession = request.getSession();
		if( httpSession.getAttribute("uid") == null) {
			response.sendRedirect("/web/login.html");
			return false;
		}
		return true;
	}
}
