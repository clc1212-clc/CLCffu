package com.myproject.BookManage.service.ex;

public class BookAlreadyRemovedException extends ServiceException {

	private static final long serialVersionUID = -379505424864497170L;

	public BookAlreadyRemovedException() {
		super();
	}

	public BookAlreadyRemovedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BookAlreadyRemovedException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookAlreadyRemovedException(String message) {
		super(message);
	}

	public BookAlreadyRemovedException(Throwable cause) {
		super(cause);
	}

	
}
