package com.myproject.BookManage.util;

/**
 * 服务器返回的数据类型 JsonResult
 * @author clc
 *
 * @param <E> 泛型
 */
public class JsonResult<E> {

	// 状态码
	private Integer state;
	// 返回消息
	private String message;
	// 返回的数据类型
	private E data;
	
	public JsonResult() {
		super();
	}
	
	public JsonResult(Integer state) {
		super();
		this.state = state;
	}
	
	public JsonResult(Integer state, E data) {
		super();
		this.state = state;
		this.data = data;
	}
	
	public JsonResult(Throwable e) {
		super();
		this.message = e.getMessage();
	}

	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	
	
	
}
