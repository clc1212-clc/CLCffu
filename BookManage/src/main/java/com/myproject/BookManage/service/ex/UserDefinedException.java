package com.myproject.BookManage.service.ex;

public class UserDefinedException extends ServiceException {

	private static final long serialVersionUID = 6976164706494728577L;

	public UserDefinedException() {
		super();
	}

	public UserDefinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserDefinedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserDefinedException(String message) {
		super(message);
	}

	public UserDefinedException(Throwable cause) {
		super(cause);
	}

	
}
