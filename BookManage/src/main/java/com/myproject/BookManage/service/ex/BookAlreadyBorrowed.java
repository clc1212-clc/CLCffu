package com.myproject.BookManage.service.ex;

public class BookAlreadyBorrowed extends ServiceException {

	private static final long serialVersionUID = 8683859280064460830L;

	public BookAlreadyBorrowed() {
		super();
	}

	public BookAlreadyBorrowed(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BookAlreadyBorrowed(String message, Throwable cause) {
		super(message, cause);
	}

	public BookAlreadyBorrowed(String message) {
		super(message);
	}

	public BookAlreadyBorrowed(Throwable cause) {
		super(cause);
	}

	
}
