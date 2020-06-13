package com.myproject.BookManage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myproject.BookManage.VO.BookEntityVO;
import com.myproject.BookManage.entity.BookEntity;
import com.myproject.BookManage.entity.ExtraMoney;

/**
 * 书本类的业务类
 * @author clc
 *
 */
public interface BorrowedService {

	/**
	 * 通过用户id两表查询书本信息
	 * @param uid 用户id
	 * @return 书本VO的实体类
	 */
	List<BookEntityVO> showBookVOByUid(Integer uid);
	
	/**
	 * 通过bid将图书信息从借阅表中删除
	 * @param bid 图书id
	 * @return 
	 */
	void removeBookFromTableByBid(Integer bid);
	
}
