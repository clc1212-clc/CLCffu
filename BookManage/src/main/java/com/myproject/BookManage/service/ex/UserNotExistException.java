package com.myproject.BookManage.service.ex;

public class UserNotExistException extends ServiceException {
	
	private static final long serialVersionUID = -2898447772379686789L;

	public UserNotExistException() {
		super();
	}

	public UserNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotExistException(String message) {
		super(message);
	}

	public UserNotExistException(Throwable cause) {
		super(cause);
	}

	
}
