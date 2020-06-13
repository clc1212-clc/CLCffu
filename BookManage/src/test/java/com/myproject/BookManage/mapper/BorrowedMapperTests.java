package com.myproject.BookManage.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myproject.BookManage.VO.BookEntityVO;
import com.myproject.BookManage.entity.BookEntity;

/**
 * 用户持久层接口的测试类
 * @author clc
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowedMapperTests {

	@Autowired
	BorrowedMapper mapper;
	
	@Test
	public void findBookByUid () {
		Integer uid = 7;
		List<BookEntityVO> result = mapper.findBookByUid(uid);
		for (BookEntityVO bookEntity : result) {
			System.err.println(bookEntity);
		}
	}
	
	@Test
	public void deleteBookByBid () {
		Integer bid = 15;
		Integer rows = mapper.deleteBookByBid(bid);
		System.err.println(rows);
	}
}
