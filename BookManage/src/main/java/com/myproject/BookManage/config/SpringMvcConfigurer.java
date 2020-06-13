package com.myproject.BookManage.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.myproject.BookManage.Interceptor.LoginInterceptor;

/**
 * 配置类 配置拦截器黑白名单
 * @author clc
 *
 */

@Configuration public class SpringMvcConfigurer implements WebMvcConfigurer {

	@Override public void addInterceptors(InterceptorRegistry registry) {
		HandlerInterceptor interceptor = new LoginInterceptor();

		List<String> list = new ArrayList<String>();
		list.add("/web/bookList.html");
		list.add("/web/index.html");
		list.add("/web/reg.html");
		list.add("/web/login.html");
		list.add("/web/searchList.html");
		
		list.add("/web/bootstrap3/**");
		list.add("/web/css/**");
		list.add("/web/images/**");
		list.add("/web/js/**");
		
		list.add("/users/reg");
		list.add("/users/login");
		list.add("/books/showAllBooks");
		list.add("/books/page");
		list.add("/books/searchPage");
		
		registry.addInterceptor(interceptor)
		.addPathPatterns("/**")
		.excludePathPatterns(list);

	}

}

