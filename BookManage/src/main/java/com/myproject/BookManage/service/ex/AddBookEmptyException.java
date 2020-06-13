package com.myproject.BookManage.service.ex;

public class AddBookEmptyException extends ServiceException {

	private static final long serialVersionUID = -2566863903476998441L;

	public AddBookEmptyException() {
		super();
	}

	public AddBookEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AddBookEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddBookEmptyException(String message) {
		super(message);
	}

	public AddBookEmptyException(Throwable cause) {
		super(cause);
	}

	
}
