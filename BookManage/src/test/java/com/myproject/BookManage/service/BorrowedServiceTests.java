package com.myproject.BookManage.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myproject.BookManage.VO.BookEntityVO;
import com.myproject.BookManage.entity.BookEntity;
import com.myproject.BookManage.service.ex.ServiceException;

/**
 * 书本业务层接口的测试类
 * @author clc
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowedServiceTests {

	@Autowired
	BorrowedService service;
	
	@Test
	public void showBookVOByUid () {
		Integer uid = 7;
		List<BookEntityVO> result = service.showBookVOByUid(uid);
		for (BookEntityVO bookEntityVO : result) {
			System.err.println(bookEntityVO);
		}
	}
	
}
