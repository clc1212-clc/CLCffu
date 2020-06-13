package com.myproject.BookManage.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.myproject.BookManage.controller.ex.ControllerException;
import com.myproject.BookManage.service.ex.ServiceException;
import com.myproject.BookManage.util.JsonResult;

/**
 * 异常处理类
 * @author clc
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ServiceException.class,ControllerException.class})
	public JsonResult<Void> handleException(Throwable e){
		JsonResult<Void> jsonResult = new JsonResult<Void>(e);

		switch (e.getClass().getName()) {
		case "com.myproject.BookManage.service.ex.BookAlreadyExistException":
			jsonResult.setState(4000);
			break;
		case "com.myproject.BookManage.service.ex.BookAlreadyRemovedException":
			jsonResult.setState(4001);
			break;
		case "com.myproject.BookManage.service.ex.BookNotExistException":
			jsonResult.setState(4002);
			break;
		case "com.myproject.BookManage.service.ex.UserNotExistException":
			jsonResult.setState(4003);
			break;
		case "com.myproject.BookManage.service.ex.BookAlreadyBorrowed":
			jsonResult.setState(4004);
			break;
		case "com.myproject.BookManage.service.ex.InsertException":
			jsonResult.setState(5000);
			break;
		case "com.myproject.BookManage.service.ex.UpdateException":
			jsonResult.setState(5001);
			break;
		case "com.myproject.BookManage.service.ex.AddBookEmptyException":
			jsonResult.setState(5002);
			break;
		case "com.myproject.BookManage.service.ex.BorrowedBookAlreadyFull":
			jsonResult.setState(5003);
			break;
		case "com.myproject.BookManage.service.ex.UserAlreadyExistException":
			jsonResult.setState(5004);
			break;
		case "com.myproject.BookManage.controller.ex.UserIsNotManageException":
			jsonResult.setState(5005);
			break;
				
		}

		return jsonResult;
	}
}
