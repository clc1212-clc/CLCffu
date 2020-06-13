package com.myproject.BookManage.service.ex;

public class SelectException extends ServiceException {
	
	private static final long serialVersionUID = -7452454936496463024L;

	public SelectException() {
		super();
	}

	public SelectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SelectException(String message, Throwable cause) {
		super(message, cause);
	}

	public SelectException(String message) {
		super(message);
	}

	public SelectException(Throwable cause) {
		super(cause);
	}

	
}
