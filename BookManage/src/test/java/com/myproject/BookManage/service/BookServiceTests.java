package com.myproject.BookManage.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myproject.BookManage.entity.BookEntity;
import com.myproject.BookManage.service.ex.ServiceException;

/**
 * 书本业务层接口的测试类
 * @author clc
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTests {

	@Autowired
	BookService service;
	
	@Test
	public void getNextBid () {
		Integer nextBid = service.getNextBid();
		System.err.println(nextBid);
	}
	
	@Test
	public void addBorrowedBookAndUser () {
	}
	
	@Test
	public void showConfirmedBook () {
		try {
			Integer[] bids = {10, 11, 12, 19};
			List<BookEntity> result = service.showConfirmedBook(bids);
			for (BookEntity bookEntity : result) {
				System.err.println(bookEntity);
			}
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
}
