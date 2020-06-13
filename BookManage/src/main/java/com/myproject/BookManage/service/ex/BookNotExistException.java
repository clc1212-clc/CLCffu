package com.myproject.BookManage.service.ex;

public class BookNotExistException extends ServiceException {

	private static final long serialVersionUID = 7960491008672678168L;

	public BookNotExistException() {
		super();
	}

	public BookNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BookNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookNotExistException(String message) {
		super(message);
	}

	public BookNotExistException(Throwable cause) {
		super(cause);
	}

	
}
