package com.myproject.BookManage.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myproject.BookManage.VO.BookEntityVO;
import com.myproject.BookManage.entity.BookEntity;

/**
 * 处理书本数据的持久层接口
 * @author clc
 *
 */
public interface BookMapper {

	/**
	 * 添加图书至库中
	 * @param book 图书的实体类
	 * @return 受影响的行数
	 */
	Integer insertBook(BookEntity book);
	
	/**
	 * 从库中根据书本id bid进行书本的删除
	 * @param bid 书本id
	 * @return 受影响的行数
	 */
	Integer deleteByBid(Integer bid);
	
	/**
	 * 图书信息的修改
	 * @param book 图书实体类
	 * @return 受影响的行数
	 */
	Integer updateBook(BookEntity book);
	
	/**
	 * 查询书库中id最大的书的id
	 * @return 最大id
	 */
	Integer findMaxBid();
	
	/**
	 * 根据借书人的id增加他借书的书的id与借书人的id
	 * @param bids 图书们id
	 * @param uid 借书人id
	 * @return 受影响的行数
	 */
	Integer beBorrowedByUid(
			@Param("bid") Integer bid, 
			@Param("uid") Integer uid,
			@Param("borrowedTime") Date borrowedTime);
	
	/**
	 * 改变图书的为被租借状态
	 * @param bid 图书id
	 * @return 受影响的行数
	 */
	Integer changeBookStatetoBorrowed(Integer bid);
	
	/**
	 * 改变图书为未被租借状态
	 * @param bid
	 * @return
	 */
	Integer changeBookStatetoFree(Integer bid);
	
	/**
	 * 查询现有的图书数量
	 * @return
	 */
	Integer countBooks();
	
	/**
	 * 模糊查询现有的图书数量
	 * @return
	 */
	Integer fuzzyCountBooks(String keyword);
	
	/**
	 * 根据图书名称查询图书的所有信息
	 * @param bid 图书id
	 * @return 受影响的行数
	 */
	BookEntity findByTitle(String title);
	
	/**
	 * 根据图书id查询图书的所有信息
	 * @param bid
	 * @return 图书的所有信息
	 */
	BookEntity findInfoByBid(Integer bid);
	
	/**
	 * 查询所有的图书信息
	 * @return 图书信息的集合
	 */
	List<BookEntity> findBooks();
	
	/**
	 * 通过图书们的id得到图书们的信息
	 * @param bids 图书们的id
	 * @return 图书信息的集合
	 */
	List<BookEntity> findConfirmedBook(Integer[] bids);
	
	/**
	 * 通过关键字模糊查询图书信息
	 * @param keyword 关键字
	 * @return 图书信息的集合
	 */
	List<BookEntity> searchByKeyword(String keyword);
	
	/**
	 * 通过每页显示的数目以及跳过的页数查询书本信息
	 * @param offset 要跳过的查询的数量
	 * @param recordCountPerPage 每页的数量
	 * @return 图书的集合
	 */
	List<BookEntity> findBookByPage(
			@Param("offset") Integer offset, 
			@Param("recordCountPerPage") Integer recordCountPerPage);
	
	/**
	 * 分页模糊查询
	 * @param keyword 关键字
	 * @param offset  要跳过的查询的数量
	 * @param recordCountPerPage 每页的数量
	 * @return 图书的集合
	 */
	List<BookEntity> searchPageByKeyword(
			@Param("keyword")String keyword,
			@Param("offset") Integer offset, 
			@Param("recordCountPerPage") Integer recordCountPerPage);
	
}
