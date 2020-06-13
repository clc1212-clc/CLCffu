package com.myproject.BookManage.service;

import java.util.List;

import com.myproject.BookManage.entity.BookEntity;
import com.myproject.BookManage.util.PageData;


/**
 * 书本类的业务类
 * @author clc
 *
 */
public interface BookService {

	/**
	 * 添加图书的业务层
	 * @param bookEntity 图书的实体类
	 */
	void addBook(BookEntity bookEntity);
	
	/**
	 * 删除图书的业务层
	 * @param bid 图书的id
	 */
	void deleteBook(Integer bid);
	
	/**
	 * 查询图书的所有信息
	 * @param bid
	 * @return
	 */
	List<BookEntity> findAllBooks();
	
	/**
	 * 查询下一个需要的bid
	 * @return 下一个添加对象的bid
	 */
	Integer getNextBid();
	
	/**
	 * 通过图书id获取图书信息
	 * @param bid 图书id
	 * @return 图书实体类
	 */
	BookEntity getBookInfo(Integer bid);
	
	/**
	 * 通过图书id更改图书信息
	 * @param bid 图书id
	 * @return 受影响的行数
	 */
	void updateBookInfo(BookEntity book);
	

	
	/**
	 * 通过图书们的id显示要确认借的书的信息
	 * @param bids 图书们的id
	 * @return 确认借的书的信息的集合
	 */
	List<BookEntity> showConfirmedBook(Integer[] bids);

	/**
	 * 添加借书人和被借走的书
	 * @param bids 书本id的數組
	 * @param uid 借书人id
	 */
	void addBorrowedBookAndUser(Integer[] bids, Integer uid);
	
	/**
	 * 通过关键字模糊查询图书
	 * @param keyword 关键字
	 * @return 图书集合
	 */
	List<BookEntity> fuzzySearchBook(String keyword);
	
	/**
	 * 通过关键字模糊查询图书
	 * @param keyword 关键字
	 * @return 图书集合
	 */
	PageData fuzzySearchBookPage(String keyword, Integer page);
	
	/**
	 * 分页显示图书列表
	 * 
	 * @param uid  用户id
	 * @param page 页码
	 * @return 收藏列表
	 */
	PageData showListByPage(Integer page);

}
