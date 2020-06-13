package com.myproject.BookManage.service.ex;

public class BookAlreadyExistException extends ServiceException {

	private static final long serialVersionUID = -4595494844831189356L;

	public BookAlreadyExistException() {
		super();
	}

	public BookAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BookAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookAlreadyExistException(String message) {
		super(message);
	}

	public BookAlreadyExistException(Throwable cause) {
		super(cause);
	}

	
}
