package com.myproject.BookManage.service.ex;

public class BorrowedBookAlreadyFull extends ServiceException {

	private static final long serialVersionUID = -2566863903476998441L;

	public BorrowedBookAlreadyFull() {
		super();
	}

	public BorrowedBookAlreadyFull(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BorrowedBookAlreadyFull(String message, Throwable cause) {
		super(message, cause);
	}

	public BorrowedBookAlreadyFull(String message) {
		super(message);
	}

	public BorrowedBookAlreadyFull(Throwable cause) {
		super(cause);
	}

	
}
