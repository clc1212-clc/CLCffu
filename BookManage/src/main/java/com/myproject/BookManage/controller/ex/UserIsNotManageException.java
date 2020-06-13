package com.myproject.BookManage.controller.ex;

public class UserIsNotManageException extends ControllerException {

	private static final long serialVersionUID = -3920895517969848367L;

	public UserIsNotManageException() {
		super();
	}

	public UserIsNotManageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserIsNotManageException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserIsNotManageException(String message) {
		super(message);
	}

	public UserIsNotManageException(Throwable cause) {
		super(cause);
	}

	
}
