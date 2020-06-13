package com.myproject.BookManage.mapper;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myproject.BookManage.entity.BookEntity;


/**
 * 书本持久层接口的测试类
 * @author clc
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMapperTests {

	@Autowired
	BookMapper mapper;
	
	@Test
	public void beBorrowedByUid() {
		Integer uid = 7;
		Integer bid = 15;
		Integer count = mapper.beBorrowedByUid(bid, uid, new Date());
		System.err.println(count);
	}
	
	@Test
	public void searchByKeyword () {
		List<BookEntity> result = mapper.searchByKeyword("红");
		System.err.println(result);
	}
	
	@Test
	public void fuzzyCountBooks () {
		Integer result = mapper.fuzzyCountBooks("红");
		System.err.println(result);
	}
	
	@Test
	public void countBooks () {
		Integer count = mapper.countBooks();
		System.err.println(count);
	}
	
	@Test
	public void findBookByPage () {
		List<BookEntity> result = mapper.findBookByPage(20, 20);
		for (BookEntity bookEntity : result) {
			System.err.println(bookEntity);
		}
	}
	
}
	
