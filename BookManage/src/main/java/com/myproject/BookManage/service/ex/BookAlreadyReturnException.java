package com.myproject.BookManage.service.ex;

public class BookAlreadyReturnException extends ServiceException {

	private static final long serialVersionUID = 4976922669701322308L;

	public BookAlreadyReturnException() {
		super();
	}

	public BookAlreadyReturnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BookAlreadyReturnException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookAlreadyReturnException(String message) {
		super(message);
	}

	public BookAlreadyReturnException(Throwable cause) {
		super(cause);
	}

	
}
