package com.myproject.BookManage.service.ex;

public class UserAlreadyExistException extends ServiceException {

	private static final long serialVersionUID = 8758698590486763891L;

	public UserAlreadyExistException() {
		super();
	}

	public UserAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAlreadyExistException(String message) {
		super(message);
	}

	public UserAlreadyExistException(Throwable cause) {
		super(cause);
	}

	
}
