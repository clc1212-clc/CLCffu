package com.myproject.BookManage.mapper;

import java.util.List;

import com.myproject.BookManage.VO.BookEntityVO;
import com.myproject.BookManage.entity.BorrowedEntity;

public interface BorrowedMapper {
	
	/**
	 * 根据用户id查询用户已租借的书本数量
	 * @param uid 用户id
	 * @return 已租借的书本数量
	 */
	Integer countByUid(Integer uid);
	
	/**
	 * 根据用户id查询用户已租借的书的信息
	 * @param uid 用户id
	 * @return 已租借的书的信息的集合
	 */
	List<BookEntityVO> findBookByUid(Integer uid);
	
	/**
	 * 根据图书id从借阅表中删除数据
	 * @param bid 图书id
	 * @return 受影响的行数
	 */
	Integer deleteBookByBid(Integer bid);
	
	/**
	 * 通过bid查询借阅表中图书的信息
	 * @param bid 图书id
	 * @return 借阅表实体类
	 */
	BorrowedEntity findBookByBid(Integer bid);
	
}
